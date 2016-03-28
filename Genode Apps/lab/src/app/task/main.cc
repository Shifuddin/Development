#include <base/printf.h>
#include "include/gthread.h"

// struct to hold threads and their precedence relation		
typedef struct thread_node
{
	GThread thread;
	const char* thread_name;
	thread_node *next;
}thread_node;

// function to show precedence relation
void print_precedence_graph(thread_node *node)
{
	Genode::printf("Thread precedence......\n");

	while(node != NULL)
	{
		Genode::printf("%s\n", node->thread_name);
		node = node->next;
	}
	
	 Genode::printf("...........\n");
	
}

// function to start threads	
void start_threads(thread_node *node)
{
	while (node != NULL)
	{
		node->thread.start();
		node = node->next;
	}
}

int main()
{

	// create threads
	GThread thread0(200,"thread one"); 
	GThread thread1(300, "thread two");
	GThread thread2(200, "thread three");
	
	// create thread_node with precedence
	thread_node third_node = {thread2, "thread three", NULL};
	thread_node second_node = {thread1,"thread two",&third_node};
	thread_node first_node  = {thread0, "thread one",&second_node};
	
	//show threads precedence relations
	print_precedence_graph(&first_node);

	// start threads
	start_threads(&first_node);
	
	return 0;
}
