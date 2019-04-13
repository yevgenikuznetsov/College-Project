/*
 * child.c
 *
 *  Created on: Dec 20, 2018
 *      Author: yevgeni
 */

#include <stdio.h>
#include "child.h"

//initiation the features of the child
void iniChild(Child* theChild) {
	int userInputId, userInputAge;
	printf("ID No:\n");
	scanf("%d", &userInputId);

	theChild->idNumber = userInputId;

	printf("Age:\n");
	scanf("%d", &userInputAge);

	theChild->age = userInputAge;

}

//print information about the child
void printChild(const Child* theChild) {

	printf("%d %d\n", theChild->idNumber, theChild->age);
}

//write information to the file about the children
void writeChildToFile(FILE* fp, const Child* theChild) {

	fprintf(fp, "%d %d\n", theChild->idNumber, theChild->age);
}

//read information about the child from file
void readChildFromFile(FILE* fp, Child* theChild) {

	if (fp != NULL) {

		fscanf(fp, "%d %d\n", &theChild->idNumber, &theChild->age);
	}

}

