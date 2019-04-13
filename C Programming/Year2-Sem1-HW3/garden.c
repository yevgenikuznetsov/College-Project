/*
 * garden.c
 *
 *  Created on: Dec 22, 2018
 *      Author: yevgeni
 */

#include "garden.h"
#include "stdio.h"
#include <stdlib.h>
#include <string.h>

const char* kinderGardenType[NUMOFTYPE] = { "Chova", "Trom Chova",
		"Trom Trom Chova" };

//print kindergarten information for user
void printGarden(const Kindgarden* theKinderGarden) {
	int i;

	printf("%s %s %d:\n", theKinderGarden->gardenName,
			kinderGardenType[theKinderGarden->typeOfGarden],
			theKinderGarden->numOfChild);

	for (i = 0; i < theKinderGarden->numOfChild; i++)
		printChild(theKinderGarden->child[i]);

}

//save the new information about kindergarten to file
void writeGardenToFile(FILE* fp, const Kindgarden* theKinderGarden) {
	int i;
	fprintf(fp, "%s %d %d\n", theKinderGarden->gardenName,
			theKinderGarden->typeOfGarden, theKinderGarden->numOfChild);

	for (i = 0; i < theKinderGarden->numOfChild; i++)
		writeChildToFile(fp, theKinderGarden->child[i]);
}

//read information about kindergarten from file
int readGardenFromFile(FILE* fp, Kindgarden* theKinderGarden) {
	int i;

	char temp[100];
	fscanf(fp, "%s", temp);

	theKinderGarden->gardenName = strdup(temp);
	if (!theKinderGarden->gardenName)
		return 0;

	fscanf(fp, "%d %d:\n", &theKinderGarden->typeOfGarden,
			&theKinderGarden->numOfChild);

	if (theKinderGarden->numOfChild == 0) {
		theKinderGarden->child = NULL;
		return 1;
	}

	theKinderGarden->child = (Child**) malloc(
			sizeof(Child*) * theKinderGarden->numOfChild);
	if (!theKinderGarden->child)
		return 0;

	for (i = 0; i < theKinderGarden->numOfChild; i++) {
		theKinderGarden->child[i] = (Child*) malloc(sizeof(Child));
		if (!theKinderGarden->child[i])
			return 0;
		readChildFromFile(fp, theKinderGarden->child[i]);
	}
	return 1;
}

//add new kindergarten in city , take all information to create new kindergarten
int initKindGardedn(char* name, Kindgarden* theKinderGarden) {
	int userChoiceGaredednType, userChoiceNumOfChild;

	theKinderGarden->gardenName = strdup(name);
	if (!theKinderGarden->gardenName)
		return 0;

	do {
		printf("Garden type:\n"
				"Enter 0 for Chova\n"
				"Enter 1 for Trom Chova\n"
				"Enter 2 for Trom Trom Chova\n");

		scanf("%d", &userChoiceGaredednType);
	} while (checkTheChooseType(userChoiceGaredednType) != 1);

	theKinderGarden->typeOfGarden = userChoiceGaredednType;

	printf("Enter children Details:\n"
			"children count:\n");
	scanf("%d", &userChoiceNumOfChild);

	theKinderGarden->numOfChild = userChoiceNumOfChild;
	initChilds(theKinderGarden);

	return 1;
}

//create array with children in the kindergarten and initialize features of child
int initChilds(Kindgarden* theKinderGarden) {
	int i;

	theKinderGarden->child = (Child**) malloc(
			sizeof(Child*) * theKinderGarden->numOfChild);
	if (!theKinderGarden->child)
		return 0;

	for (i = 0; i < theKinderGarden->numOfChild; i++) {
		theKinderGarden->child[i] = (Child*) malloc(sizeof(Child));

		if (!theKinderGarden->child[i])
			return 0;

		iniChild(theKinderGarden->child[i]);
	}

	return 1;
}

//check if the user choose regular type of kindergarten
int checkTheChooseType(int userChoice) {
	if (userChoice == Chova || userChoice == TromChova
			|| userChoice == TromTromChova) {
		return 1;
	}
	printf("invalid type kindergarten\n");
	return 0;
}

//check if the child exists in the kindergarten , if not crate new child
void addChildrenToGarden(Kindgarden* theKinderGarden) {
	int userInputId;

	printf("ID No:\n");
	scanf("%d", &userInputId);

	if (checkIfKindExsist(theKinderGarden, userInputId)) {
		initChildrenToGarden(theKinderGarden, userInputId);
	}

}

//check if the child exists in the kindergarten
int checkIfKindExsist(Kindgarden* theKinderGarden, int id) {
	int i;

	for (i = 0; i < theKinderGarden->numOfChild; i++) {
		if (theKinderGarden->child[i]->idNumber == id) {
			printf("the child is exists\n");
			return 0;
		}
	}

	return 1;
}

//create new space in array kindergarten for add more children
int initChildrenToGarden(Kindgarden* theKinderGarden, int id) {

	theKinderGarden->child = (Child**) realloc(theKinderGarden->child,
			sizeof(Child*) * (theKinderGarden->numOfChild + 1));
	if (!theKinderGarden->child)
		return 0;

	theKinderGarden->child[theKinderGarden->numOfChild] = (Child*) malloc(
			sizeof(Child));
	if (!theKinderGarden->child[theKinderGarden->numOfChild])
		return 0;

	if (!saveIdAndAge(id, theKinderGarden))
		return 0;

	theKinderGarden->numOfChild++;

	return 1;
}

//save the new information about new children
int saveIdAndAge(int id, Kindgarden* theKinderGarden) {
	int userInputAge;

	theKinderGarden->child[theKinderGarden->numOfChild]->idNumber = id;

	printf("Age:\n");
	scanf("%d", &userInputAge);

	theKinderGarden->child[theKinderGarden->numOfChild]->age = userInputAge;

	return 1;
}

//add age to children in the kindergarten
void addAge(Kindgarden* theKinderGarden, int id) {
	int i;

	for (i = 0; i < theKinderGarden->numOfChild; i++) {
		if (theKinderGarden->child[i]->idNumber == id) {
			theKinderGarden->child[i]->age++;

		}
	}
}

//release the kindergarten allocations
void freeGarden(Kindgarden* theKinderGarden) {
	freeChildinGarden(theKinderGarden);
	free(theKinderGarden->gardenName);
	free(theKinderGarden->child);
}

//release the children in specific kindergarten allocations
void freeChildinGarden(Kindgarden* theKinderGarden) {
	int i;

	for (i = 0; i < theKinderGarden->numOfChild; i++) {
		free(theKinderGarden->child[i]);
	}

}
