/*
 * function.h
 *
 *  Created on: Nov 16, 2018
 *      Author: yevgeni
 */

#ifndef FUNCTION_H_
#define FUNCTION_H_

#define MAX 99
#define MIN 1
#define MAXNUMBEROFTIME 30
#define MINFORRANDOM 0
#define NUMBERTODIVIDE 2
#define MAXTORANDOM 10
#define MINTORANDOM 2


void PrintMat(int* mat, int rows, int cols);
void flipVertical(int* mat, int size);
void flipHorizonatal(int* mat, int size);
void CLockWise(int* mat, int size);
void CounterClockWise(int* mat, int size);
void initRandomArr(int* mat, int size);
void createMat(int* mat, int rows, int cols);
void randomMAt(int* mat, int cols, int rows);
int matInIndex(int* mat, int cols, int rows);
int checkMat(int* mat, int row, int cols);
int moveIndex(int* mat, int row, int col, int currentEmptyIndex,int playerIndex);
int getRandomNumber();
int metrixSize();
int getNumber();

#endif /* FUNCTION_H_ */
