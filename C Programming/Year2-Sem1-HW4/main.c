#include <stdio.h>
#include <stdlib.h>

#include "General.h"
#include "Kindergarten.h"
#include "Child.h"
#include "City.h"

int main(int argc, char* argv[]) {

	if (argc < 2) {
		printf(
				"You must enter 1 for binary file 0 for test file\n For example ./HW4");
		return -1;
	}

	int opt;
	sscanf(argv[1], "%d", &opt);

	int num = opt;

	City utz = { NULL, 0 };
	int uReq;

	//first time read
	readCity(&utz, num);
	do {
		uReq = menu();
		switch (uReq) {
		case READ_CITY:
			readCity(&utz, num);
			break;

		case SHOW_CITY:
			showCityGardens(&utz);
			break;

		case SHOW_GARDEN:
			showSpecificGardenInCity(&utz);
			break;

		case WRITE_CITY:
			saveCity(&utz, num);
			break;

		case ADD_GARDEN:
			cityAddGarden(&utz);
			break;

		case ADD_CHILD:
			addChildToSpecificGardenInCity(&utz);
			break;

		case CHILD_BIRTHDAY:
			birthdayToChild(&utz);
			break;

		case COUNT_GRADUATE:
			printf("There are %d children going to school next year\n",
					countChova(&utz));
			break;

		case SORT_BY_NAME_GARDEN:
			sortByNameGarden(&utz);
			break;

		case SORT_BY_NAME_CHILD:
			sortById(&utz);
			break;

		case SORT_BY_TYPE_AND_NUM_OF_CHILD:
			sortByTypeAndNumOfChildrenInTheKndedrgarten(&utz);
			break;

		case LINKED_LIST:
			LinkedList(&utz);
			break;

		}
	} while (uReq != EXIT);

	releaseCity(&utz); //free all allocations

	return EXIT_SUCCESS;
}

