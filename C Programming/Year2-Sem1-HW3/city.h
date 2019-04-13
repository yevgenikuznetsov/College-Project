/*
 * city.h
 *
 *  Created on: Dec 22, 2018
 *      Author: yevgeni
 */

#ifndef CITY_H_
#define CITY_H_

#include "garden.h"

#define NAME "DataFile.txt"

typedef struct {

	int numOfGarden;
	Kindgarden** kindGarden;

} City;

void showCityGardens(City* city);
int saveCity(City* city);
int readCity(City* city);
void showSpecificGardenInCity(City* city);
int cityAddGarden(City* city);
void addChildToSpecificGardenInCity(City* city);
void birthdayToChild(City* city);
int countChova(City* city);
void ReleaseCity(City* city);
int checkthename(char nameGardenFromUser[100], City* city, int* index);

#endif /* CITY_H_ */
