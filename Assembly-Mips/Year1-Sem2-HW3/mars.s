# Title: hw_6 	 
# Author: yevgeni kuznetsov , id: 319498580    
# Author: aviv tefilin , id: 313459869
# Input: 
# Output:
################# Data segment #####################
.data

NUM : .word 0
Array: .word 0:30
JumpTable : .word  top, case1, case2, case3, case4, case5, case6, case7, case8, case9 	

all_options : .asciiz "    \nThe options are:\n\n 1. Enter a number (base 10)\n 2. Replace a number (base 10)\n 3. DEL a number (base 10)\n 4. Find a number in the array (base 10)\n 5. Find average (base 2-10)\n 6. Find Max (base 2-10)\n 7. Print the array elements (base 2-10)\n 8. Print sort array (base 2-10)\n 9. END \n "
option : .asciiz  "\n    your option: "     
is_full: .asciiz "    The array is full.\n\n"
num_to_add: .asciiz "    What number to add? "
add_suc: .asciiz "    The number entered to array successfully\n"
is_exsist : .asciiz "    The number already exists, is value: "  
is_add: .asciiz "    Add successfully\n"
empty: .asciiz "    The array is empty\n"
replace : .asciiz "\n    What number to replace? "
go_top: .asciiz "\n    The number successfully entered\n"
replace_number: .asciiz "    Which number to replace "
replace_number_1: .asciiz " (in index "    
replace_number_2: .asciiz ") with what number ? "
replace_number_3: .asciiz "\n    Which number replace one of the numbers "
go_to_top11: .asciiz  "\n    We can't replace this num \n"
del: .asciiz "\n    What number to delete? "
no_del: .asciiz "\n    The number doest exist and therefore can not be erase\n"
del_1: .asciiz "    The number deleted successfully\n"
find_num: .asciiz "    What number to find? "
print_average: .asciiz "    Which base to print (between 10-2): "
print_num_base: .asciiz "    The number by base"
max_mess: .asciiz "\n    The number index is: "
wrong_text: .asciiz "    You need to choose num between 1-9"
################# Code segment #####################
.text
wrong: 
	la $a0,wrong_text	  #Incorrect reception message
	li $v0,4		
	syscall		

.globl main

main:	# main program entry

la $a1 ,NUM              # load num address to $a1
la $a2 ,Array            # load array address to $a2

top:

  la $a0 ,all_options    # print string message (menu)
  li $v0 ,4
  syscall

  la $a0 ,option	 # print string message (Please select a number)
  li $v0 ,4
  syscall
 
  li $v0 ,5		 # Takes the choose number from the user
  syscall 
 
  bgt $v0 , 9 ,wrong     # check if input number is appropriate
  blt $v0 , 1 , wrong 	 # check if input number is appropriate
	
 la $a3, JumpTable       # load label to jump from jump table
 sll $t0 ,$v0 ,2	 # $t0=index*4
 add $t1 , $a3, $t0	 # Calculates the address of the label
 lw $t2 ,0($t1)          # store the label address to register
 jalr  $t2


 case1:                 # Enters numbers into an array that receives them from the user
  jal add_number
  j top

 case2:                 # Replaces number one in array with another number
  jal Replace 
  j top

 case3:                 # Deleting a number from array
  jal DEL
  j top

 case4:                 # Find number in array
  jal find
  j top

 case5:                 # Calculate the average of the array
  jal average
  j top

 case6:                 # Find the maximum number in the array
  jal max
  j top

 case7:                 # Printing the array elements
  jal print_array
  j top

 case8:                 # Doesn exist
  j top

 case9:                 #exit from program
  j exit

exit:
	li $v0,10
	syscall
	
################################################################################################################################################
#                                                                  Procedures                                                                  #
################################################################################################################################################

###########################################################
#  add_number : $t4 - array size , $a2 - array            #
###########################################################

add_number:
	lw $t4 ,0($a1)                   # load size array to $t4
	addiu $sp, $sp, -4           
	sw $a1,0($sp)		         # store the addres $a1 in stack
	
	bgt $t4 , 30 ,full               # check if array is full
	
	move $a1 ,$t4

 	la $a0 ,num_to_add               # print string message (to enter number)
	li $v0 ,4
	 syscall 
 
 	li $v0 ,5                        # Takes the number from the user
 	syscall			

 	addiu $sp, $sp, -4            
	sw $ra,0($sp)                    # jump and link to address in $ra
	la $t3 ,check		         # load check address to $t3
	jalr $t3                         # jump and link to address in $t3
	lw $ra,($sp)                     # return address to $ra
	addiu $sp, $sp, 4        
	 
	beq  $v0 , -1 ,add_to_array1     # if $v0 not= add this number
	
	move $t5,$v0                     # move contain from $v0 to $t5
	la $a0 , is_exsist               # print string message (the number exist)
	li $v0 ,4
	syscall 
	
	move $a0,$t5                     # print integer(the index of number)
	li $v0 ,1 
	syscall 
	
	jr $ra 

        add_to_array1:
	    mul $t3 ,$t4 ,4             # mul size array with 4 (4 = word byte)
	    add $t2 ,$t3 ,$a2           # the addres to store the num
	    sw $t6 ,0($t2)              # store the num in array
	    addi $t4 ,$t4 ,1            # add +1 to total size array
	    lw $a1,0($sp)               # return num address to $a1
	    addiu $sp,$sp,4
	    sw $t4,0($a1)               # store the size array to $a1

	
	    la $a0 ,is_add              # print string message (the number is add)
	    li $v0 ,4 
	    syscall
	
	    jr $ra
	
               full:
                   la $a0 ,is_full      # print string message (the array is full)
                   li $v0 ,4
                   syscall
                   j top


###########################################################
#  Replace : $t4 - array size , $a2 - array               #
###########################################################	
	
Replace:
	addiu $sp, $sp, -4
	sw $ra,0($sp)	              # jump and link to address in $ra
	la $t2 ,empty_1               # load empty address to $t2
	jalr $t2                      # jump and link to address in $t2
	lw $ra,($sp)                  # return address to $ra
	addiu $sp, $sp, 4
	
	la $a0 , replace              # print string message (replace)
	li $v0 ,4
	syscall 
	
	li $v0 ,5                     # print integer( number to replace)
	syscall 
	
	addiu $sp, $sp, -4
	sw $ra,0($sp)                 # jump and link to address in $ra
	la $t3 ,check                 # load check address to $t3
	jalr $t3                      # jump and link to address in $t3
	lw $ra,($sp)                  # return address to $ra
	addiu $sp, $sp, 4
	
	beq $v0 , -1 , go_to_top      
	
	move $t7 ,$v0                 # move contain from $v0 to $t7
		
	la $a0 ,replace_number        # print string message (number to replace)
	li $v0,4
	syscall 
	
	move $a0 ,$t6                 # print integer( number index)
	li $v0 ,1
	syscall 
	
	la $a0,replace_number_1       # print string message (number to replace)
	li $v0,4
	syscall 
	
	move $a0 ,$t7                 # print integer( the number to replace)
	li $v0,1
	syscall 
	
	la $a0,replace_number_2       # print string message (number to replace)
	li $v0,4
	syscall 

	li $v0,5                     # print integer( number from user)
	syscall 
	
	addiu $sp, $sp, -4       
	sw $v0, 0($sp)               # jump and link to address in $v0
	
	addiu $sp, $sp, -4            
	sw $ra,0($sp)                # jump and link to address in $ra
	la $t3 ,check                # load check address to $t3
	jalr $t3                     # jump and link to address in $t3
	lw $ra,($sp)                 # return address to $ra
	addiu $sp, $sp, 4
	
	bne $v0 ,-1 ,go_to_top1
	
	lw $v0, 0($sp)               # return address to $v0
	addiu $sp, $sp, 4
	
	mul $t7 ,$t7 ,4              # mul size array with 4 (4 = word byte)
	add $t2 ,$t7 ,$a2            # the addres to store the num
	sw $v0 ,0($t2)               # store the new number
	
	jr $ra
	
	
###########################################################
#  Replace : $a1 - array size , $a2 - array               #
###########################################################
	
DEL: 
        addiu $sp, $sp, -4          
	sw $ra,0($sp)	            # jump and link to address in $ra
	la $t2 ,empty_1             # load empty address to $t2
	jalr $t2                    # jump and link to address in $t2
	addiu $sp, $sp, -4
	sw $a1,0($sp)               # return address to $ra
	
	la $a0 ,del                 # print string message (delet number)
	li $v0,4
	syscall 
	
	li $v0,5                    # print integer( number from user to del)
	syscall 
	
	addiu $sp, $sp, -4 
	sw $ra,0($sp)               # jump and link to address in $ra
	la $t3 ,check               # load check address to $t3
	jalr $t3                    # jump and link to address in $t3
	lw $ra,($sp)                # return address to $ra
	addiu $sp, $sp, 4           
		
	bne $v0 ,-1 ,to_erase
	la $a0 ,no_del              # print string message (delet number dosnt exist)
	li $v0 ,4
	syscall 
	
	sw $t5,0($a1)               # store the size array to $a1  
	jr $ra
	
        to_erase:
	      addiu $sp, $sp, -4  
	      sw $ra,0($sp)         # jump and link to address in $ra
	      la $t3 ,reduction     # load raduction address to $t3
	      jalr $t3              # jump and link to address in $t3
	      lw $ra,($sp)          # return address to $ra
	      addiu $sp, $sp, 4

	j top
	
	
###########################################################
#                         find                            #
###########################################################
find:
	la $a0 ,find_num           # print string message (find number)
	li $v0 ,4
	syscall 
	
	li $v0, 5                  # print integer( number from user to find)
	syscall 
	
	addiu $sp, $sp, -4 
	sw $ra,0($sp)              # jump and link to address in $ra
	la $t3 ,check              # load check address to $t3
	jalr $t3                   # jump and link to address in $t3
	lw $ra,($sp)               # return address to $ra
	addiu $sp, $sp, 4
	
	beq $v0 ,-1 , go_to_top
	move $t5 ,$v0              # move contain from $v0 to $t5
	
	la $a0 , is_exsist         # print string message (the number exist)
	li $v0 ,4
	syscall 
	
	move $a0 ,$t5              # print integer(index of number)
	li $v0 ,1
	syscall 
	
	jr $ra
	
	
###########################################################
#  average : $t4 - array size , $t2 - array               #
###########################################################
average:
	move $t5 ,$t4              # move contain from $t4 to $t5
	move $t2 ,$a2              # move contain from $a2 to $t2
	li $t9 ,0
	
       loop_2:
	    lw $t7 ,0($t2)          # $t7 number from array
	    add $t9 ,$t9 ,$t7       # Total calculation
	    addi $t2 ,$t2 ,4        # add to address +4 (4 = word byte )
	    addi $t5,$t5,-1         # count -1
	    bne $t5 ,$0,loop_2      # if $t5 not= 0 go to loop

	    div $t9 ,$t4            # div the total number
	    mflo $t9                # $t9 = the Complete Part
	
	    sw $a1, 0($sp)          # jump and link to address in $a1
	    addiu $sp, $sp, -4
	    sw $a2, 0($sp)          # jump and link to address in $a2
	    addiu $sp, $sp, -4
	
            again_1:
	         la $a0 ,print_average # print string message (base)
	         li $v0 ,4
	         syscall 
	
	         li $v0 ,5             # print integer(witch base to convert)
	         syscall 
	
	         bgt $v0 , 10 ,  again_1 # check if input number is appropriate
                 blt $v0 , 2 ,  again_1  # check if input number is appropriate
	
	        addiu $sp, $sp, -4
	        sw $ra,0($sp)           # jump and link to address in $ra
	        la $t3 ,print_num       # load check address to $t3
	        jalr $t3                # jump and link to address in $t3
	        lw $ra,0($sp)           # return address to $ra
	        addiu $sp, $sp, 4
	
	        lw $a1, 0($sp)          # return address to $a1
	        addiu $sp, $sp, 4
	        lw $a2, 0($sp)          # return address to $a2
	        addiu $sp, $sp, 4
	
	jr $ra
	
###########################################################
#  max : $t4 - array size , $t2 - array                   #
###########################################################
max:
	move $t2 ,$a2           # move contain from $a2 to $t2
	move $t5,$t4            # move contain from $t4 to $t5
	
	lw $t9,0($t2)           # default the max number 
	addi $t5,$t5,-1         # count -1 num of numbers
	
    loop_4:
	  addi $t2,$t2,4        # add to address +4 (4 = word byte )
	  lw $t7,0($t2)         # take the next number from array

	  bgt $t9 ,$t7, no_change  # if firs num > second num
	  move $t9 , $t7           # change th max number
	
        no_change: 
 	      addi $t5,$t5,-1      # counter -1 num of numbers
	      bne $t5,$0,loop_4    # if num od number = 0
 
             again:
 	        la $a0 ,print_average  # print string message (base)
	        li $v0 ,4
	        syscall 
	
	        li $v0 ,5              # print integer(witch base to convert)   
	        syscall
 	
 	        bgt $v0 , 10 , again          # check if input number is appropriate
                blt $v0 , 2 ,  again          # check if input number is appropriate
 	
 
	        addiu $sp, $sp, -4
	        sw $a1, 0($sp)                 # store $a1 address in stake 
	        addiu $sp, $sp, -4
	        sw $a2, 0($sp)                 # store $a2 address in stake 
	        addiu $sp, $sp, -4
	        sw $t9, 0($sp)                 # store $t9 in the stack

	        addiu $sp, $sp, -4
	        sw $ra,0($sp)                  # jump and link to address in $ra
	        la $t3 ,print_num              # load check address to $t3
	        jalr $t3                       # jump and link to address in $t3
	        lw $ra,0($sp)                  # return address to $ra
	        addiu $sp, $sp, 4
	
	
	        la $a0,max_mess                # print string message (the max number)
	        li $v0 ,4
	        syscall 
	
	        lw $t9, 0($sp)                 # load from stack the $t9
	        addiu $sp, $sp, 4  
	
	        move $v0 ,$t9                  # move contain from $t9 to $v0              
	
	        lw $a2, 0($sp)                 # load $a2 addres from stack 
	        addiu $sp, $sp, 4
	
	        addiu $sp, $sp, -4
	        sw $ra,0($sp)                  # jump and link to address in $ra
	        la $t3 ,check                  # load check address to $t3
	        jalr $t3                       # jump and link to address in $t3
	        lw $ra,($sp)                   # return address to $ra
	        addiu $sp, $sp, 4
	
	        move $a0 ,$v0                  # print integer(max number)   
	        li $v0 ,1
	        syscall 	

	        lw $a1, 0($sp)                 # store $a1 address to stack
	        addiu $sp, $sp, 4
	        
	jr $ra
	
###########################################################
#  print_array : $t4 - array size , $t2 - array           #
###########################################################

print_array:
	move $t2 ,$a2	             # move contain from $a2 to $t2
	move $t5 ,$t4                # move contain from $t4 to $t5
			
	la $a0 ,print_average        # print string message (wich base)
	li $v0 ,4
	syscall 
	
	li $v0 ,5                    # print integer(base) 
	syscall  
	
	 bgt $v0 , 10 ,print_array   # check if input number is appropriate
         blt $v0 , 2 , print_array   # check if input number is appropriate
         
        addiu $sp, $sp, -4        
	sw $a2, 0($sp)               # store $a2 address in stake
	addiu $sp, $sp, -4
	sw $a1, 0($sp)               # store $a1 address in stake
	
	move $t7 ,$v0                # move contain from $v0 to $t7
	
     loop_5:
	   move $v0 ,$t7                # move contain from $t7 to $v0
	
	   lw $t9 ,0($t2)               # load number from array
	   addi $t2 ,$t2 ,4             # add to address +4 (4 = word byte ) 
			
	   addiu $sp, $sp, -4
	   sw $ra,0($sp)                # jump and link to address in $ra
	   la $t3 ,print_num            # load check address to $t3
	   jalr $t3                     # jump and link to address in $t3
	   lw $ra,0($sp)                # return address to $ra
           addiu $sp, $sp, 4
	 
	   li $v0,11                    # print char
	   la $a0, ' '
	   syscall

	   addi $t5 ,$t5 ,-1            # counter +1 for loop
	   bne $t5 , 0 , loop_5         # if $t5 not= 0 go to loop
	
	   lw $a1, 0($sp)               # return $a1 address from stack
	   addiu $sp, $sp, 4

	   lw $a2, 0($sp)               # return $a2 address from stack
	   addiu $sp, $sp, 4
	
	jr $ra


###########################################################
#                      go_to_top1                         #
###########################################################
go_to_top1:
      la $a0 , go_to_top11             # print string message 
      li $v0 ,4
      syscall 

      j top
	
	
###########################################################
#                      go_to_top                          #
###########################################################
go_to_top:	
      la $a0 ,go_top
      li $v0 ,4
      syscall 

      j top
      
###############################################################
#  check : $t4 - array size , $t2 - array , $v0 - user number #               
###############################################################
check:
  move $t6 ,$v0                    # move contain from $v0 to $t6
  li $v0 ,0     
  move $t8 ,$a2                    # move contain from $a2 to $t8

  loop:
 	  lw $t9,0($t8)          #load  number in array
 	  beq $t9 ,$t6 ,sent     # if $t6 = $t9 go to sent
 	  addi $t8,$t8,4         # add to address +4 (4 = word byte ) 
 	  addi $v0,$v0,1         # counter +1 
 	  blt $v0,$t4,loop       # chack $t4 < $v0
 	  li $v0 ,-1             
 	
     sent:
	   jr $ra
	   
###############################################################
# empty_1 : $t4 - array size                                  #               
###############################################################
empty_1:
	bne  $t4 ,$0 ,go     # if size array = 0 show message
	la $a0 , empty       # print string message 
	li $v0 ,4
	syscall 
	
	j top
	
      go:
	   jr $ra
	

###############################################################
# empty_1 : $t4 - array size, $a2 - array                     #               
###############################################################		
reduction:
   move $t5 ,$t4             # move contain from $t4 to $t5
	
   beq $t5 ,$v0 ,loop_1_2    # if index num = $t5
   mul $t7 ,$v0 ,4           # mul index*4 for address
   add $t8 ,$t7 ,$a2         # Calculate address
	
     loop_1:
	lw $t3,4($t8)        # load +1 number in array
	sw $t3 ,0($t8)       # store number in arrray
	
	addi $v0,$v0,1       # add +1 to $v0
	addi $t8,$t8,4       # add address for array
	
	bgt $t5, $v0 ,loop_1 # of $t5 > $v0 go to loop
	
         loop_1_2:
	      addi $t5 ,$t5,-1  # count -1
	      move $t4 ,$t5     # move contain from $t5 to $t4

	      sw $t5, 0($a1)    # store new num of number to $a1

        jr $ra
        
        
###############################################################
# print_num : $a1 - number  , $a2 - base  $t3 -counter        #               
###############################################################	       
print_num:
  move $a1 ,$t9                # move contain from $t9 to $a1
  move $a2 ,$v0                # move contain from $v0 to $a2

	li $t3 , 0
	bgt $a1 ,0,loop_3      # if 0 > $a1 go to loop
	abs $a1,$a1            # absolute value of number
	
	li $t7 ,'-'            # print char if number negative
	move $a0,$t7
	li $v0,11
	syscall 
	
	
     loop_3:		

	  div $a1 ,$a2         # div $a1 by $a2
	  mfhi $t8             # $t8 the remainder of the integer

	  addiu $sp, $sp, -4
	  sw $t8 ,0($sp)       # store $t8 to tha stack
	
	  mflo $a1            
	  addi $t3 ,$t3 ,1     # $t3 +1
	
	  bne $a1 ,0 ,loop_3   # if $a1 not = go to loop
	
        print_loop_3:

	     lw $a0,0($sp)                 # return $t8 from tha stack
	     li $v0 ,1                     # print integer (the remainder)
	     syscall 
	     addiu $sp, $sp, 4  
	     addi $t3 ,$t3 ,-1             #counter -1
	     bne $t3 ,$0, print_loop_3     # if $t3 not = 0 go to loop
	
	jr $ra
	


