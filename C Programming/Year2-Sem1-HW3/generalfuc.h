/*
 * generalfuc.h
 *
 *  Created on: Dec 25, 2018
 *      Author: yevgeni
 */

#ifndef GENERALFUC_H_
#define GENERALFUC_H_

typedef enum {
	EXIT,
	READ_CITY,
	SHOW_CITY,
	SHOW_GARDEN,
	WRITE_CITY,
	ADD_GARDEN,
	ADD_CHILD,
	CHILD_BIRTHDAY,
	COUNT_GRADUATE
}TypeMenu;

void printMenu();
int menu();

#endif /* GENERALFUC_H_ */
