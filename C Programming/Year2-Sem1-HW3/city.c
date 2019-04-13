/*
 * city.c
 *
 *  Created on: Dec 22, 2018
 *      Author: yevgeni
 */

#include "city.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


//print the information about the city
void showCityGardens(City* city) {
	int i;
	for (i = 0; i < city->numOfGarden; i++) {
		printGarden(city->kindGarden[i]);
	}
}

//save the new information to file
int saveCity(City* city) {

	FILE* fp;
	int i;

	fp = fopen(NAME, "w");
	if (!fp)
		return -1;

	fprintf(fp, "%d", city->numOfGarden);
	for (i = 0; i < city->numOfGarden; i++) {
		writeGardenToFile(fp, city->kindGarden[i]);
	}

	fclose(fp);
	return 0;
}

//read from file the information
int readCity(City* city) {

	FILE* fp;
	int i;

	fp = fopen(NAME, "r");
	if (!fp)
		return 0;
	fscanf(fp, "%d", &city->numOfGarden);
	if (!city->numOfGarden)
		return 0;

	city->kindGarden = (Kindgarden**) malloc(
			city->numOfGarden * sizeof(Kindgarden*));

	if (!city->kindGarden) {
		fclose(fp);
		return 0;
	}

	for (i = 0; i < city->numOfGarden; i++) {
		city->kindGarden[i] = (Kindgarden*) malloc(sizeof(Kindgarden));
		if (!city->kindGarden[i]) {
			fclose(fp);
			return 0;
		}

		readGardenFromFile(fp, city->kindGarden[i]);

	}

	fclose(fp);
	return 1;
}

//show information about kindergarten that user select
void showSpecificGardenInCity(City* city) {
	char nameGardenFromUser[100];
	int i;

	printf("Enter the name of kindergarten:");
	scanf("%s", nameGardenFromUser);

	if (checkthename(nameGardenFromUser, city, &i)) {
		printGarden(city->kindGarden[i]);
	}
}

//add new garden to city
int cityAddGarden(City* city) {
	char nameOfTheNewGareden[100];
	int i;
	int indication = 0;

	printf("Enter the name of kindergarten:");
	scanf("%s", nameOfTheNewGareden);

	if (checkthename(nameOfTheNewGareden, city, &i)) {
		printf("The KindGarden is exists\n");
		indication = 1;
	}

	if (indication == 0) {

		city->kindGarden = (Kindgarden**) realloc(city->kindGarden,
				sizeof(Kindgarden*) * (city->numOfGarden + 1));
		if (!city->kindGarden)
			return 0;

		city->kindGarden[city->numOfGarden] = (Kindgarden*) malloc(
				sizeof(Kindgarden));
		if (!city->kindGarden[city->numOfGarden])
			return 0;
		if (!initKindGardedn(nameOfTheNewGareden,
				city->kindGarden[city->numOfGarden]))
			return 0;
		city->numOfGarden++;
		return 1;

	}
	return 1;
}

//add children to kindergarten in the city
void addChildToSpecificGardenInCity(City* city) {
	char nameGardenFromUser[100];

	printf("Enter the name of kindergarten:");
	scanf("%s", nameGardenFromUser);

	int i;

	if (checkthename(nameGardenFromUser, city, &i)) {
		addChildrenToGarden(city->kindGarden[i]);
	}

}

//update the child's age in specific kindergarten in the city
void birthdayToChild(City* city) {
	char nameGardenFromUser[100];

	int i, id;

	printf("Enter the name of kindergarten:");
	scanf("%s", nameGardenFromUser);

	if (checkthename(nameGardenFromUser, city, &i)) {

		printf("Please enter the id:\n");
		scanf("%d", &id);

		if (checkIfKindExsist(city->kindGarden[i], id) == 0) {
			addAge(city->kindGarden[i], id);
		}

	}

}

//count how many children present in kinderGarten whose type is Chova
int countChova(City* city) {
	int i;
	int count = 0;

	for (i = 0; i < city->numOfGarden; i++) {
		if (city->kindGarden[i]->typeOfGarden == 0) {
			count = count + city->kindGarden[i]->numOfChild;
		}
	}

	return count;
}

//release the city allocations
void ReleaseCity(City* city) {
	int i;

	for (i = 0; i < city->numOfGarden; i++) {
		freeGarden(city->kindGarden[i]);
	}
	free(city->kindGarden);

}

//check if the kindergarten that user typed is in the city
int checkthename(char nameGardenFromUser[100], City* city, int* index) {
	int i;
	for (i = 0; i < city->numOfGarden; i++) {
		if (strcmp((city->kindGarden[i]->gardenName), nameGardenFromUser)
				== 0) {
			*index = i;
			return 1;
		}
	}
	return 0;
}

