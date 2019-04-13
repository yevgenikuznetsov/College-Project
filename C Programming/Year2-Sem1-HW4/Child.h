#ifndef __CHILD__
#define __CHILD__


typedef struct
{
	int	 id;
	int  age;
}Child;


void	readChildTxt(FILE* fp, Child* pChild);
void	readChildBin(FILE* fp, Child* pChild);

void	getChildFromUser(Child* pChild, int id);
void	showChild(const Child* pChild);
void	writeChildTxt(FILE* fp,const Child* pChild);
void	writeChildBin(FILE* fp,const Child* pChild);

int		findChildById(Child** pChildList, int count, int id);
void	birthday(Child* pChild);
//void	releaseChild(Child* pChild);
#endif
