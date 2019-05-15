/*
 * hw1.h
 *
 *  Created on: May 3, 2019
 *      Author: yevgeni
 */

#ifndef HW1_H_
#define HW1_H_

typedef struct {
	int** solution;
	int status[3];
	char task[3];
} shared;

typedef struct {
	int sudoku_array[81];
} array;

int** read_From_File(char* argv);
int** createMatrix();

void with_mmap(int** sudoku , char* argv);
void with_pipe(array sudoku_array, char* argv);

void check_sudoku(shared* shared , char* argv);
char* createPipe(int pfd[]);
void create_Fork(char* infro[], int pfd[]);
char* createPipeForFather(int pfd[], array sudoku_array);
void conver_To_Array(int** sudoku, int array[81]);


#endif /* HW1_H_ */
