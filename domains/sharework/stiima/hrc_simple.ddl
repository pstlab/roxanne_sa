DOMAIN SHAREWORK_HRC_SIMPLE
{
	TEMPORAL_MODULE temporal_module = [0, 100], 100;
	
	PAR_TYPE EnumerationParameterType configurations = {
		P0, P1, P2, P3, P4, P5, P6, P7 
	};
	
	PAR_TYPE NumericParameterType risk_levels = [0, 5];
	
	
	COMP_TYPE SingletonStateVariable ProcessType (
		Idle(), HRC())
	{
		VALUE Idle() [1, +INF]
		MEETS {
			HRC();
		}
		
		VALUE HRC() [1, +INF]
		MEETS {
			Idle();
		}
	}
	
	COMP_TYPE SingletonStateVariable HumanType (Idle(), H1(), H2(), H3(), H4(), H5())
	{
		VALUE Idle() [1, +INF]
		MEETS {
			H1();
			H2();
			H3();
			H4();
			H5();
		}
		
		VALUE H1() [3, +INF]
		MEETS {
			Idle();
		}
		
		VALUE H2() [7, +INF]
		MEETS {
			Idle();
		}
		
		VALUE H3() [11, +INF]
		MEETS {
			Idle();
		}
		
		
		VALUE H4() [4, +INF]
		MEETS {
			Idle();
		}
		
		
		VALUE H5() [5, +INF]
		MEETS {
			Idle();
		}
	}
	
	
	COMP_TYPE SingletonStateVariable RobotType (Idle(), R1(risk_levels), R2(risk_levels), R3(risk_levels), R4(risk_levels), R5(risk_levels))
	{
		VALUE Idle() [1, +INF]
		MEETS {
			R1(?r1);
			R2(?r2);
			R3(?r3);
			R4(?r4);
			R5(?r5);
		}
		
		VALUE R1(?risk) [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE R2(?risk) [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE R3(?risk) [1, +INF]
		MEETS {
			Idle();
		}
		
		
		VALUE R4(?risk) [1, +INF]
		MEETS {
			Idle();
		}
		
		
		VALUE R5(?risk) [1, +INF]
		MEETS {
			Idle();
		}	
	}
	
	COMP_TYPE SingletonStateVariable RobotMotionType (At(configurations, risk_levels), _Move(configurations, configurations, risk_levels))
	{
		VALUE At(?config, ?risk) [1, +INF]
		MEETS {
			_Move(?from, ?to, ?level);
			?from = ?config;
		}
		
		VALUE _Move(?from, ?to, ?level) [5, 11]
		MEETS {
			At(?config, ?risk);
			?config = ?to;
			?level = ?risk;
		}
	}
	
	COMPONENT Process {FLEXIBLE hrc(functional)} : ProcessType;
	COMPONENT Human {FLEXIBLE activities(primitive)} : HumanType;
	COMPONENT Robot {FLEXIBLE tasks(functional)} : RobotType;
	COMPONENT RobotArm {FLEXIBLE motion(primitive)} : RobotMotionType;
	
	SYNCHRONIZE Process.hrc
	{
		VALUE HRC()
		{
			t0 <!> Human.activities.H1();
			t1 <!> Robot.tasks.R2(?r1);
			t2 <!> Human.activities.H3();
			t3 <!> Robot.tasks.R4(?r4);
			t4 <!> Robot.tasks.R5(?r5);
			
			CONTAINS [0, +INF] [0, +INF] t0;
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			
			?r1 = 1;
			?r4 = 4;
			?r5 = 3;
			
			c01 RobotArm.motion.At(?cfg0, ?risk0);
			c02 Human.activities.Idle();
			c03 Robot.tasks.Idle();
			
			STARTS-DURING [0, +INF] [0, +INF] c01;
			STARTS-DURING [0, +INF] [0, +INF] c02;
			STARTS-DURING [0, +INF] [0, +INF] c03;
			
			?cfg0 = P0;
			?risk0 = 1;
		}
	}
	
	SYNCHRONIZE Robot.tasks
	{
		VALUE R1(?risk)
		{
			t1 RobotArm.motion.At(?cfg1, ?r1);
			
			ENDS-DURING [0, +INF] [0, +INF] t1;
			
			?r1 = ?risk;
			?cfg1 = P1;  
		}
		
		VALUE R2(?risk)
		{
			t1 RobotArm.motion.At(?cfg1, ?r1);
			
			ENDS-DURING [0, +INF] [0, +INF] t1;
			
			?r1 = ?risk;
			?cfg1 = P2;  
		}
		
		VALUE R3(?risk)
		{
			t1 RobotArm.motion.At(?cfg1, ?r1);
			
			ENDS-DURING [0, +INF] [0, +INF] t1;
			
			?r1 = ?risk;
			?cfg1 = P3;  
		}
		
		VALUE R4(?risk)
		{
			t1 RobotArm.motion.At(?cfg1, ?r1);
			
			ENDS-DURING [0, +INF] [0, +INF] t1;
			
			?r1 = ?risk;
			?cfg1 = P4;  
		}
		
		VALUE R5(?risk)
		{
			t1 RobotArm.motion.At(?cfg1, ?r1);
			
			ENDS-DURING [0, +INF] [0, +INF] t1;
			
			?r1 = ?risk;
			?cfg1 = P5;  
		}
	}
}
