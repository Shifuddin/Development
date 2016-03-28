#ifndef __TASK_GTHREAD_H_
#define __TASK_GTHREAD_H_
#include <base/thread.h>
#include <base/printf.h>
#define THREAD_STACK_SIZE 2000
using namespace Genode;

class GThread: public Thread_base
{
	public:
		// constructor 
		// instialization of this class creates new thread
		GThread (size_t weight, const char *name)
		:Thread_base(weight, name, THREAD_STACK_SIZE, Type::NORMAL)
		{

		}

		 /**
		 * Entry method of the thread
		 */
		
		void entry()
		{
			int i = 0;
			for (i =0; i<5;i++)
			{
				Genode::printf("Thread entry function %d\n",i);
			}

		}
	

};


#endif
