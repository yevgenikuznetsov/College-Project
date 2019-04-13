/*
 * func.h
 *
 *  Created on: Dec 7, 2018
 *      Author: yevgeni
 */

#ifndef FUNC_H_
#define FUNC_H_

#define  MAX_LEN 100


void initString(char* str, int size);
void printString(char* str);
int countWords(char* str);
void longestInCaptital(char* str);
void changeToUpper(char* str, long beginnungWord, int counterMax);
void revertWords(char* str);
void swap(char* str, int count);
void eraseCharsFromString(char* str, const char* symbols);
int isPalindrome(const char *str);


#endif /* FUNC_H_ */
