/*Programmer: Michael Habib
Project #6
Create a method that finds the shortest path through
a given grid. Also created a queue class and stack class.
Implemented the Stack as an array and the queue as a linked list.
*/  
   class coord {
      private int row, col;//(row,col) of grid
   //constructor
      public coord(int row, int col) {
         this.row=row;
         this.col=col;
      }
   //getters for row and column
      public int getRow(){
         return row;
      }
      public int getColumn(){
         return col;
      }
   
   }
   class node {
      private coord data;//coordinate
      private node next;//node/pointer
   //constructor
      public node(coord data,node next){
         this.data=data;
         this.next=next;
      }//getters for node and data coordinates
      public node getNext(){
         return next;
      }
      public coord getData(){
         return data;
      }
   //setters for node and data coordinates
      public void setNext(node pointer){next=pointer;}
   }
 
   class queue{
      private node front_pt, back_pt;
   //constructor
      public queue() {
         front_pt = null;
         back_pt = null;
      }
   //returns the front of the queue
      public coord front() {
         coord front;
         if(front_pt!=null){
            front= front_pt.getData();
         }
         else
            front=null;
         return front;
      }
   //checks to see if the queue is empty
      public boolean empty() {
         return front_pt==null;
      }
   //removes the front of the queue
      public void dequeue() {
         if(front_pt!=null)
            front_pt=front_pt.getNext();
         else{}
      }
   //adds a coordinate to the end of the queue
   
      public void enqueue(coord data) {
         if(front_pt==null){//when empty front and back pt point to same thing
            back_pt=new node(data,null);
            front_pt=back_pt;
         }
         else{//creates new node then sets back_pt to it
            back_pt.setNext(new node(data,null));
            back_pt=back_pt.getNext();
         }
      	
      }
   }
   
   class stack{
      private coord coordStack[];
      private int top;
   	// Constructor for stack
      public stack(){
         coordStack=new coord[200];
         top=-1;
      }
   	//while stack is not empty return top of stack
      public coord top(){
         coord topOfStack;
         if(top!=-1)
            topOfStack=coordStack[top];
         else
            topOfStack=null;
         return topOfStack;
      }
   	//checks to see if stack is empty
      public boolean empty(){
         return top==-1;
      }
   	//push onto the stack
      public void push(coord coordinate){
         coordStack[top+1]=coordinate;
         top++;
      }
   	//pop off stack
      public void pop(){
         if(top!=-1)
            top --;
         else{}
      }
   }
   class path{
      public static void shortest_path(int[][] grid, int startRow, int startCol,
      int goalRow, int goalCol){
         int counter=0;//compiler was complaining so I had to initialize it
         queue pathQueue=new queue();//queue that will traverse grid
         int row=startRow,column=startCol;
      //checks to see if start and goal are the same
         if(startRow==goalRow&&startCol==goalCol){
            counter=1;//when start and goal are the same path is length 1
         				// and the path is just the starting coordinate
            System.out.println("There is a path of length "+counter);
            System.out.print("("+goalRow+", "+goalCol+")");
         }//startRow and startCol of grid =0, no path
         else if(grid[startRow][startCol]==0)
            System.out.println("No path through the grid.");
         else{//start and goal arent the same continue with finding path
            pathQueue.enqueue(new coord(startRow,startCol));//enqueue start coordinate
            counter=2;//variable that will mark grid 
         				//started at two to avoid issue with lots of 1's lying around(initially)
            grid[startRow][startCol]=0;//set starting point to 0 to prevent 
         									// it being set to a neighbor
            if(column!=0){//if not first column check left
               if(grid[row][column-1]==1){//legal move , enqueue left 
                  pathQueue.enqueue(new coord(row,column-1));
                  grid[row][column-1]=counter;
               }
            }
            if(row!=0){//if not first row check up
               if(grid[row-1][column]==1){
                  pathQueue.enqueue(new coord(row-1,column));//legal move , enqueue up
                  grid[row-1][column]=counter;
               
               }
            }
            if(column!=grid[0].length-1){//if not last column check right
               if(grid[row][column+1]==1){
                  pathQueue.enqueue(new coord(row,column+1));//legal move , enqueue right
                  grid[row][column+1]=counter;
               
               }
            }
            if(row!=grid.length-1){//if not last row check down
               if(grid[row+1][column]==1){
                  pathQueue.enqueue(new coord(row+1,column));//legal move , enqueue down
                  grid[row+1][column]=counter;
               
               }
            }
         
            pathQueue.dequeue();//dequeueu coordinate after looking at all its neighbors
            if(pathQueue.empty())//if empty no path through the grid
               System.out.println("No path through the grid.");
            //moving on to next  set of neighbors
            else{// condition: path is not empty and 
            	//front of queueu is not the goal coordinate
               while(!pathQueue.empty()&&!(pathQueue.front().getRow()==goalRow&&pathQueue.front().getColumn()==goalCol)){
                  //counter will be the front coordinate marker +1 to add neighbors
                  counter=grid[pathQueue.front().getRow()][pathQueue.front().getColumn()]+1;
               	//row and column set to front coordinate
                  row=pathQueue.front().getRow();
                  column=pathQueue.front().getColumn();
                  if(column!=0){//if not first column check left
                     if(grid[row][column-1]==1){//legal move , enqueue left 
                        pathQueue.enqueue(new coord(row,column-1));
                        grid[row][column-1]=counter;
                     }
                  }
               
                  if(row!=0){//if not first row check up
                     if(grid[row-1][column]==1){
                        pathQueue.enqueue(new coord(row-1,column));//legal move , enqueue up
                        grid[row-1][column]=counter;
                     
                     }
                  }
                  if(column!=grid[0].length-1){//if not last column check right
                     if(grid[row][column+1]==1){
                        pathQueue.enqueue(new coord(row,column+1));//legal move , enqueue right
                        grid[row][column+1]=counter;
                     
                     }
                  }
                  if(row!=grid.length-1){//if not last row check down
                     if(grid[row+1][column]==1){
                        pathQueue.enqueue(new coord(row+1,column));//legal move , enqueue down
                        grid[row+1][column]=counter;
                     
                     }
                  }
                  pathQueue.dequeue();
               }
               if(pathQueue.empty())//empty queueu no path
                  System.out.println("No path through the grid.");
               else{
               //counter is set to what the counter makred the goal coord
                  counter=grid[goalRow][goalCol];
               //output starting point of path
                  System.out.println("There is a path of length "+counter);
                  System.out.print("("+startRow+", "+startCol+")-");
                  grid[startRow][startCol]=1;//set start coord of grid back to 1
               	//set row and col to goal to prepare to go through backwards through grid
                  row=goalRow;
                  column=goalCol;
                  stack pathStack= new stack();
               	//decrement counter to check for neighbors of goal coord
                  counter--;
               
               //Time to go backwards through the path!     
               //****PRIORIT OF NEIGHBORS IS L.U.R.D(LEFT UP RIGHT DOWN)*****
               //Structure of if/else statements is set up with this priority in mind
                  while(counter!=1){
                     if(column!=0){//if not first column check left
                        if(grid[row][column-1]==counter){//correct neighbor, push
                           pathStack.push(new coord(row,column-1));
                           grid[row][column-1]=1;
                           column=column-1;
                           counter--;
                        }
                        else  if(row!=0){//if not first row check up
                           if(grid[row-1][column]==counter){//correct neighbor, push
                              pathStack.push(new coord(row-1,column));
                              grid[row-1][column]=1;
                              row=row-1;
                              counter--;
                           
                           
                           }
                           else if(column!=grid[0].length-1){//if not last column check right
                              if(grid[row][column+1]==counter){//correct neighbor, push
                                 pathStack.push(new coord(row,column+1));
                                 grid[row][column+1]=1;
                                 column=column+1;
                                 counter--;
                              
                              }
                              else if(row!=grid.length-1){//if not last row check down
                                 if(grid[row+1][column]==counter){//correct neighbor, push
                                    pathStack.push(new coord(row+1,column));
                                    grid[row+1][column]=1;
                                    row=row+1;
                                    counter--;
                                 
                                 
                                 }
                              }
                           }
                           else if(row!=grid.length-1){//if not last row check down
                              if(grid[row+1][column]==counter){//correct neighbor, push
                                 pathStack.push(new coord(row+1,column));
                                 grid[row+1][column]=1;
                                 row=row+1;
                                 counter--;
                              }
                           }
                        
                        	                              
                        }
                        else if(column!=grid[0].length-1){//if not last column check right
                           if(grid[row][column+1]==counter){//correct neighbor, push
                              pathStack.push(new coord(row,column+1));
                              grid[row][column+1]=1;
                              column=column+1;
                              counter--;
                              
                           }
                           else if(row!=grid.length-1){//if not last row check down
                              if(grid[row+1][column]==counter){//correct neighbor, push
                                 pathStack.push(new coord(row+1,column));
                                 grid[row+1][column]=1;
                                 row=row+1;
                                 counter--;
                              
                              
                              }
                           }
                        }
                        else if(row!=grid.length-1){//if not last row check down
                           if(grid[row+1][column]==counter){//correct neighbor, push
                              pathStack.push(new coord(row+1,column));
                              grid[row+1][column]=1;
                              row=row+1;
                              counter--;
                           }
                        }
                     }
                     else  if(row!=0){//if not first row check up
                        if(grid[row-1][column]==counter){//correct neighbor, push
                           pathStack.push(new coord(row-1,column));
                           grid[row-1][column]=1;
                           row=row-1;
                           counter--;
                        
                        
                        }
                        
                        else if(column!=grid[0].length-1){//if not last column check right
                           if(grid[row][column+1]==counter){//correct neighbor, push
                              pathStack.push(new coord(row,column+1));
                              grid[row][column+1]=1;
                              column=column+1;
                              counter--;
                           
                           }
                           else if(row!=grid.length-1){//if not last row check down
                              if(grid[row+1][column]==counter){//correct neighbor, push
                                 pathStack.push(new coord(row+1,column));
                                 grid[row+1][column]=1;
                                 row=row+1;
                                 counter--;
                              
                              }
                           }	
                        }
                        else if(row!=grid.length-1){//if not last row check down
                           if(grid[row+1][column]==counter){//correct neighbor, push
                              pathStack.push(new coord(row+1,column));
                              grid[row+1][column]=1;
                              row=row+1;
                              counter--;
                           
                           }
                        }
                     }
                     else if(column!=grid[0].length-1){//if not last column check right
                        if(grid[row][column+1]==counter){//correct neighbor, push
                           pathStack.push(new coord(row,column+1));
                           grid[row][column+1]=1;
                           column=column+1;
                           counter--;
                        
                        }
                        else if(row!=grid.length-1){//if not last row check down
                           if(grid[row+1][column]==counter){//correct neighbor, push
                              pathStack.push(new coord(row+1,column));
                              grid[row+1][column]=1;
                              row=row+1;
                              counter--;
                           
                           }
                        }
                     
                     }
                     else if(row!=grid.length-1){//if not last row check down
                        if(grid[row+1][column]==counter){//correct neighbor, push
                           pathStack.push(new coord(row+1,column));
                           grid[row+1][column]=1;
                           row=row+1;
                           counter--;
                        
                        }
                     }
                  }
               	//Time to output path by popping off the coordinates on the stack
						int format=1;
                  while(!pathStack.empty()){
                  //prints coord at top of stack then pops it off.
						if(format%10==0)
						System.out.println();
                     System.out.print("("+pathStack.top().getRow()+", "+pathStack.top().getColumn()+")-");
                     pathStack.pop();
							format++;
                  }
               	//prints goal coord
                  System.out.print("("+goalRow+", "+goalCol+")");
                  grid[goalRow][goalCol]=1;
               	//sets all elements of grid >1 back to 1
                  for(int i=0;i<grid.length;i++){
                     for(int j=0;j<grid[0].length;j++){
                        if(grid[i][j]>1)
                           grid[i][j]=1;
                     }
                  }
               }
            }   
         }
      }
      public static String myName() {
         return "Michael Habib";
      }
   }
