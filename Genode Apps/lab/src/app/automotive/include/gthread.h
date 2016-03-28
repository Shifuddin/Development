#ifndef __TASK_GTHREAD_H_
#define __TASK_GTHREAD_H_
#include <base/thread.h>
#include <base/printf.h>
#include "global.h"
#include "base/stdint.h"

#define THREAD_STACK_SIZE 2000
using namespace Genode;

class GThread: public Thread_base
{
		int current_thread = -1;
		umword_t operations[100];
		unsigned operation_count;
		operation op;		
        public:
                // constructor
                // instialization of this class creates new thread
                GThread (size_t weight, const char *name)
                :Thread_base(weight, name, THREAD_STACK_SIZE, Type::NORMAL)
                {
			op.set_operation();			 
                }

                 /**
                 * Entry method of the thread
                 */

		void match_critical(unsigned cr_op)
		{
			unsigned i = 0;
			for(i = 0; i < 4; i++)
			{
				if (cr_op == op.ops[0][i].op_code)
				{
					Genode::printf("%d is valid operation for thread0\n",cr_op);
					return;
				}
			}
			Genode::printf("%d is invalid operation for thread0\n",cr_op);			
		}
		void match_non_critical(unsigned cr_op)
		{
			unsigned i = 0;
                        for(i = 0; i < 4; i++)
                        {
                                if (cr_op == op.ops[1][i].op_code)
                                {
                                        Genode::printf("%d is valid operation for thread1\n",cr_op);
                                        return;
                                }
                        }
                        Genode::printf("%d is invalid operation for thread1\n",cr_op);
		}
                void entry()
                {
			
			if (current_thread == 0)
			{
				 unsigned i = 0;
                        	 for(i = 0; i < operation_count; i++ )
                   	         {
                            	    match_critical(operations[i]);
                        	 }
				
			}
                        else if (current_thread == 1)
			{
				 unsigned i = 0;
                                 for(i = 0; i < operation_count; i++ )
                                 {
                                    match_non_critical(operations[i]);
                                 }
                             
			}

                }
		
		void set_current(int current)
		{
			current_thread = current;
		}
		
		void send_operation(umword_t *data, unsigned number)
		{
			unsigned i = 0;
			for(i = 0; i < number; i++ )
			{
				operations[i] =  data[i];
			}
			
			operation_count = number;
		}

		
};
#endif
