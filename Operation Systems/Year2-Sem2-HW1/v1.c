/*
 * main.c
 *
 *  Created on: May 3, 2019
 *      Author: yevgeni
 */

#include "hw1.h"
#include <stdlib.h>
#include <stdio.h>
#include <sys/wait.h>

int main(int argc, char **argv) {
	int** sudoku;
	array array_sukuko;

	if (argc == 1) {
		sudoku = createMatrix();

		conver_To_Array(sudoku, array_sukuko.sudoku_array);

		with_pipe(array_sukuko, "your suduko");

	} else {

		int i;
		for (i = 1; i < argc; i++) {

			sudoku = read_From_File(argv[i]);

			conver_To_Array(sudoku, array_sukuko.sudoku_array);

			with_pipe(array_sukuko, argv[i]);
		}

	}

	return 1;
}
