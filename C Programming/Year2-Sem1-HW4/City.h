#ifndef __CITY__
#define __CITY__

#include "Kindergarten.h"
#include "generic.h"

#define DATA_TXT "DataFile.txt"
#define DATA_BINARY "DataFile.bin"

#define MAX_WORD 100

typedef struct {
	Garden** pGardenList;
	int count;
} City;

void readCity(City* pCity , int num);
void showCityGardens(City* pCity);
void showSpecificGardenInCity(City* pCity);
void saveCity(City* pCity,int num);
void cityAddGarden(City* pCity);
void addChildToSpecificGardenInCity(City* pCity);
void birthdayToChild(City* pCity);
int countChova(City* pCity);
void releaseCity(City* pCity);
void sortByNameGarden(City* pCity);
void sortById(City* pCity);
void sortByTypeAndNumOfChildrenInTheKndedrgarten(City* pCity);
void LinkedList(City* pcity);

LIST* createLinkedListForKindergartenType(City* pCity , int type);
void displayKindergartensFromList(LIST* pLst);

#endif
