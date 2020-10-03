DOMAIN SHAREWORK_HRC_NISSAN
{
	TEMPORAL_MODULE temporal_module = [0, 100], 100;
	
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
	
	COMP_TYPE SingletonStateVariable HumanType (
		Idle(), _H_01(), _H_02(), _H_03(), _H_04(), _H_05(), _H_06(), _H_07(), _H_08(), _H_09(), _H_10(), _H_11(), _H_12())
	{
		VALUE Idle() [1, +INF]
		MEETS {
			_H_01();
			_H_02();
			_H_03();
			_H_04();
			_H_05();
			_H_06();
			_H_07();
			_H_08();
			_H_09();
			_H_10();
			_H_11();
			_H_12();
		}
		
		VALUE _H_01() [1, 20]
		MEETS {
			Idle();
		}
		
		VALUE _H_02() [1, 20]
		MEETS {
			Idle();
		}
		
		VALUE _H_03() [1, 20]
		MEETS {
			Idle();
		}
		
		
		VALUE _H_04() [1, 20]
		MEETS {
			Idle();
		}
		
		
		VALUE _H_05() [1, 20]
		MEETS {
			Idle();
		}
		
		VALUE _H_06() [1, 20]
		MEETS {
			Idle();
		}
		
		VALUE _H_07() [1, 20]
		MEETS {
			Idle();
		}
		
		VALUE _H_08() [1, 20]
		MEETS {
			Idle();
		}
		
		VALUE _H_09() [1, 20]
		MEETS {
			Idle();
		}
		
		VALUE _H_10() [1, 20]
		MEETS {
			Idle();
		}
		
		VALUE _H_11() [1, 20]
		MEETS {
			Idle();
		}
		
		VALUE _H_12() [1, 20]
		MEETS {
			Idle();
		}
	}
	
	
	COMP_TYPE SingletonStateVariable RobotType (
		Idle(), _R_01(), _R_02()
	)
	{
		VALUE Idle() [1, +INF]
		MEETS {
			_R_01();
			_R_02();
		}
		
		VALUE _R_01() [1, 10]
		MEETS {
			Idle();
		}
		
		VALUE _R_02() [1, 10]
		MEETS {
			Idle();
		}
	}
	
	COMPONENT Process {FLEXIBLE hrc(functional)} : ProcessType;
	COMPONENT Human {FLEXIBLE activities(primitive)} : HumanType;
	COMPONENT Robot {FLEXIBLE tasks(primitive)} : RobotType;
	
	SYNCHRONIZE Process.hrc
	{
		VALUE HRC()
		{
			t0 <!> Robot.tasks._R_01();
			t1 <!> Human.activities._H_01();
			t2 <!> Human.activities._H_02();
			t3 <!> Human.activities._H_03();
			t4 <!> Human.activities._H_04();
			t5 <!> Human.activities._H_05();
			t61 <!> Robot.tasks._R_02();
			t62 <!> Human.activities._H_06();
			t7 <!> Human.activities._H_07();
			t8 <!> Human.activities._H_08();
			t9 <!> Human.activities._H_09();
			t10 <!> Human.activities._H_10();
			t11 <!> Human.activities._H_11();
			t12 <!> Human.activities._H_12();
			
			
			CONTAINS [0, +INF] [0, +INF] t0;
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t61;
			CONTAINS [0, +INF] [0, +INF] t62;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
			CONTAINS [0, +INF] [0, +INF] t10;
			CONTAINS [0, +INF] [0, +INF] t11;
			CONTAINS [0, +INF] [0, +INF] t12;
			
			t0 BEFORE [0, +INF] t1;
			t1 BEFORE [0, +INF] t2;
			t2 BEFORE [0, +INF] t3;
			t3 BEFORE [0, +INF] t4;
			t4 BEFORE [0, +INF] t5;
			t5 BEFORE [0, +INF] t61;
			t5 BEFORE [0, +INF] t62;
			t61 BEFORE [0, +INF] t7;
			t62 BEFORE [0, +INF] t7;
			t7 BEFORE [0, +INF] t8;
			t8 BEFORE [0, +INF] t9;
			t9 BEFORE [0, +INF] t10;
			t10 BEFORE [0, +INF] t11;
			t11 BEFORE [0, +INF] t12;
		}
	}
}
