/*
 * garden.h
 *
 *  Created on: Dec 22, 2018
 *      Author: yevgeni
 */

#ifndef GARDEN_H_
#define GARDEN_H_

#define NUMOFTYPE 3
#include "child.h"

typedef enum {
	Chova, TromChova, TromTromChova

} TypeOfGardedn;

typedef struct {

	char* gardenName;
	int typeOfGarden;
	int numOfChild;
	Child** child;

} Kindgarden;

void printGarden(const Kindgarden* theKinderGarden);
void writeGardenToFile(FILE* fp, const Kindgarden* theKinderGarden);
int readGardenFromFile(FILE* fp, Kindgarden* theKinderGarden);
int initKindGardedn(char* name, Kindgarden* theKinderGarden);
int checkTheChooseType(int userChoice);
int initChilds(Kindgarden* theKinderGarden);
void addChildrenToGarden(Kindgarden* theKinderGarden);
int checkIfKindExsist(Kindgarden* theKinderGarden, int id);
int initChildrenToGarden(Kindgarden* theKinderGarden, int id);
int saveIdAndAge(int id, Kindgarden* theKinderGarden);
void addAge(Kindgarden* theKinderGarden, int id);
void freeGarden(Kindgarden* theKinderGarden);
void freeChildinGarden(Kindgarden* theKinderGarden);

#endif /* GARDEN_H_ */
