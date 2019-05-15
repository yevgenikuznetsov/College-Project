/*
 * hw1.c
 *
 *  Created on: May 3, 2019
 *      Author: yevgeni
 */


#define _GNU_SOURCE
#include <string.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <stdio.h>
#include "function.h"
#include <unistd.h>
#include  "hw1.h"
#include <fcntl.h>
#include <errno.h>

#define SIZE 9
#define TOTAL 3


int** read_From_File(char* argv) {

	FILE* file = fopen(argv, "r");
	if (!file) {
		printf("Fail");

	}

	int** sudoku = (int**) malloc(SIZE * sizeof(int*));
	int i, j, num;
	for (i = 0; i < SIZE; i++) {
		sudoku[i] = (int*) malloc(SIZE * sizeof(int));
		for (j = 0; j < SIZE; j++) {
			fscanf(file, "%d", &num);
			sudoku[i][j] = num;
		}

	}
	fclose(file);

	return sudoku;
}

int** createMatrix() {
	int** temp = (int**) malloc(sizeof(int*) * SIZE);
	int i, j;

	for (i = 0; i < SIZE; i++) {
		temp[i] = (int*) malloc(sizeof(int) * SIZE);
	}

	for (i = 0; i < 9; i++) {
		for (j = 0; j < 9; j++) {
			scanf("%d", &temp[i][j]);
		}
	}

	return temp;
}

void with_mmap(int** sudoku, char* argv) {
	shared* sharde_sudoku = mmap(NULL, sizeof(shared), PROT_READ | PROT_WRITE,
	MAP_ANON | MAP_SHARED, -1, 0);
	if (sharde_sudoku == MAP_FAILED) {
		perror("mmap failed");

	}

	sharde_sudoku->task[0] = 'r';
	sharde_sudoku->task[1] = 'c';
	sharde_sudoku->task[2] = 'g';
	sharde_sudoku->solution = sudoku;

	check_sudoku(sharde_sudoku, argv);
}

void with_pipe(array sudoku_array, char* argv) {
	int res, total = 0;
	int pfd1[2], pfd2[2], pfd3[2], pfd4[2], pfd5[2], pfd6[2];

	char* str_pfd1 = createPipe(pfd1);
	char* str_pfd2 = createPipe(pfd2);
	char* str_pfd3 = createPipe(pfd3);

	char* str_pfd4 = createPipeForFather(pfd4, sudoku_array);
	char* str_pfd5 = createPipeForFather(pfd5, sudoku_array);
	char* str_pfd6 = createPipeForFather(pfd6, sudoku_array);

	char* infor1[] = { "./check", str_pfd1, "c", str_pfd4, NULL };
	char* infro2[] = { "./check", str_pfd2, "r", str_pfd5, NULL };
	char* infro3[] = { "./check", str_pfd3, "g", str_pfd6, NULL };

	create_Fork(infor1, pfd1);
	create_Fork(infro2, pfd2);
	create_Fork(infro3, pfd3);

	wait(NULL);
	wait(NULL);
	wait(NULL);

	read(pfd1[0], &res, sizeof(int));
	total = total + res;

	read(pfd2[0], &res, sizeof(int));
	total = total + res;

	read(pfd3[0], &res, sizeof(int));
	total = total + res;

	if (total == TOTAL) {
		printf("%s is LEGAL\n", argv);
	} else {
		printf("%s is NOT LEGAL\n", argv);
	}
}

void check_sudoku(shared* shared, char* argv) {

	int pid;
	int i;

	for (i = 0; i < TOTAL; i++) {
		pid = fork();

		if (pid < 0) {
			perror("fork failed");
			break;
		} else if (pid > 0) {
			wait(NULL);
		} else {
			switch (shared->task[i]) {
			case 'r':
				shared->status[i] = CheckRow(shared->solution);
				if (munmap(shared, sizeof(shared)) == -1) {
					perror("error");
				}
				break;
			case 'c':
				shared->status[i] = CheckCol(shared->solution);
				if (munmap(shared, sizeof(shared)) == -1) {
					perror("error");
				}
				break;
			case 'g':
				shared->status[i] = CheckBox(shared->solution);
				if (munmap(shared, sizeof(shared)) == -1) {
					perror("error");
				}
				break;

			}
		}
	}

	int num = 0;
	for (i = 0; i < TOTAL; i++) {
		num = num + shared->status[i];

	}
	if (num == TOTAL) {
		printf("%s is LEGAL\n", argv);
	} else {
		printf("%s is NOT LEGAL\n", argv);
	}

}

char* createPipe(int pfd[]) {

	char* str_pfd = (char*) malloc(sizeof(char) * 2);

	if (pipe(pfd) < 0) {
		perror("failed");
	}

	sprintf(str_pfd, "%d", pfd[1]);
	return str_pfd;

}

char* createPipeForFather(int pfd[], array sudoku_array) {
	if (pipe(pfd) < 0) {
		perror("failed");
	}

	write(pfd[1], sudoku_array.sudoku_array,
			sizeof(sudoku_array.sudoku_array) + 1);

	char* str_pfd = (char*) malloc(sizeof(char) * 2);
	sprintf(str_pfd, "%d", pfd[0]);

	return str_pfd;

}

void create_Fork(char* infro[], int pfd[]) {
	int pid;
	pid = fork();

	if ((pid) < 0) {
		perror("failed");

	} else if (pid == 0) {
		close(pfd[0]);
		execvp(infro[0], infro);
	}
}

void conver_To_Array(int** sudoku, int array[81]) {

	int i, j;

	for (i = 0; i < SIZE; i++) {
		for (j = 0; j < SIZE; j++) {
			*(array + i * SIZE + j) = sudoku[i][j];

		}
	}

}

