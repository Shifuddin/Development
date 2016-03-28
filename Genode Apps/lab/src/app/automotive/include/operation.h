#ifndef __AUTOMOTIVE_OPERATION_H
#define __AUTOMOTIVE_OPERATION_H

class operation
{

public:
	typedef struct Op
	{	
 		 unsigned op_code;
 		 char     op_name[100];
	} Op;

	Op       ops[2][10];
	unsigned  ops_count[2];
	int current_thread = -1;	
	public:
		operation()
		{}



		void set_operation()
		{	 
		
			// ops[0]: Critical operations
 			 ops_count[0] = 4;
			
			 ops[0][0] = (Op){ 5, "ENABLE_ALL_WHEEL_DRIVE" };
  			 ops[0][1] = (Op){ 6, "DISABLE_ALL_WHEEL_DRIVE" };
  			 ops[0][2] = (Op){ 7, "ENABLE_ELECTRONIC_STABILITY_CONTROL" };
  			 ops[0][3] = (Op){ 8, "DISABLE_ELECTRONIC_STABILITY_CONTROL" };
			
			// ops[1]: Uncritical operations
  			ops_count[1] = 4;
			
			ops[1][0] = (Op){ 10, "ENABLE_WIRELESS_LAN" }; 
  			ops[1][1] = (Op){ 11, "DISABLE_WIRELESS_LAN" };
  			ops[1][2] = (Op){ 12, "ENABLE_TRAFFIC_LIGHT_FEEDBACK" };
  			ops[1][3] = (Op){ 13, "DISABLE_TRAFFIC_LIGHT_FEEDBACK" };
			

		}

		void set_current_thread(int current)
		{
			Genode::printf("set test\n");
			current_thread = current;
		}

		int get_current_thread()
		{
			return current_thread;
		}


};

#endif
