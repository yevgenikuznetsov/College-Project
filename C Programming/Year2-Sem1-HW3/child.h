/*
 * child.h
 *
 *  Created on: Dec 20, 2018
 *      Author: yevgeni
 */

#ifndef CHILD_H_
#define CHILD_H_

#include "stdio.h"

typedef struct
{
	int idNumber;
	int age;
} Child;

void printChild(const Child* theChild);
void writeChildToFile(FILE* fp,const Child* theChild);
void readChildFromFile(FILE* fp, Child* theChild);
void iniChild(Child* theChild);






#endif /* CHILD_H_ */
