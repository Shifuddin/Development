#include <base/printf.h>
#include <base/stdint.h>
#include "include/gthread.h"
#include "include/global.h"
using namespace Genode;


int main()
{

	Genode::printf("Thread0 for critical operations\n");
	Genode::printf("Thread1 for noncritical operations\n");	
	// create threads
        GThread thread0(200, "critical");
        GThread thread1(300, "non-critical");		

	umword_t data0[2] = {6,8};
	thread0.send_operation(data0,2);
	thread0.set_current(0);
	thread0.start();	
	
	umword_t data1[3] = {10, 12, 5};
	thread1.send_operation(data1,3);
	thread1.set_current(1);
        thread1.start();
	
	Genode::printf("end\n");	
return 0;
}
