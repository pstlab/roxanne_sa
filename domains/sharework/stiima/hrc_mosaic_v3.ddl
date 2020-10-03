DOMAIN SHAREWORK_HRC_MOSAIC_v3
{
	TEMPORAL_MODULE temporal_module = [0, 7000], 100;
	
	PAR_TYPE NumericParameterType rows = [1, 5];
	
	PAR_TYPE EnumerationParameterType columns = {
		A, B, C, D, E, F, G, H, I, J
	};
	
	PAR_TYPE EnumerationParameterType configurations = {
		box_orange, box_blue, box_white,
		A1, A2, A3, A4, A5,
		B1, B2, B3, B4, B5,
		C1, C2, C3, C4, C5,
		D1, D2, D3, D4, D5,
		E1, E2, E3, E4, E5,
		F1, F2, F3, F4, F5,
		G1, G2, G3, G4, G5,
		H1, H2, H3, H4, H5,
		I1, I2, I3, I4, I5,
		J1, J2, J3, J4, J5  
	};
	
	PAR_TYPE NumericParameterType risk_levels = [0, 5];
	
	
	COMP_TYPE SingletonStateVariable ProcessType (
		Idle(), DoMosaic())
	{
		VALUE Idle() [1, +INF]
		MEETS {
			DoMosaic();
		}
		
		VALUE DoMosaic() [1, +INF]
		MEETS {
			Idle();
		}
	}
	
	COMP_TYPE SingletonStateVariable MosaicProcessType(
		Idle(), DoRow1(), DoRow2(), DoRow3(), DoRow4(), DoRow5())
	{
		VALUE Idle() [1, +INF]
		MEETS {
			DoRow1();
			DoRow2();
			DoRow3();
			DoRow4();
			DoRow5();
		}
		
		VALUE DoRow1() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoRow2() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoRow3() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoRow4() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoRow5() [1, +INF]
		MEETS {
			Idle();
		}
	}
	
	COMP_TYPE SingletonStateVariable AssemblyProcessType(
		Idle(), 
		DoCellA1(), DoCellB1(), DoCellC1(), DoCellD1(), DoCellE1(), DoCellF1(), DoCellG1(), DoCellH1(), DoCellI1(), DoCellJ1(),
		DoCellA2(), DoCellB2(), DoCellC2(), DoCellD2(), DoCellE2(), DoCellF2(), DoCellG2(), DoCellH2(), DoCellI2(), DoCellJ2(),
		DoCellA3(), DoCellB3(), DoCellC3(), DoCellD3(), DoCellE3(), DoCellF3(), DoCellG3(), DoCellH3(), DoCellI3(), DoCellJ3(),
		DoCellA4(), DoCellB4(), DoCellC4(), DoCellD4(), DoCellE4(), DoCellF4(), DoCellG4(), DoCellH4(), DoCellI4(), DoCellJ4(),
		DoCellA5(), DoCellB5(), DoCellC5(), DoCellD5(), DoCellE5(), DoCellF5(), DoCellG5(), DoCellH5(), DoCellI5(), DoCellJ5())
	{
		VALUE Idle() [1, +INF]
		MEETS {
			DoCellA1(); DoCellB1(); DoCellC1(); DoCellD1(); DoCellE1(); DoCellF1(); DoCellG1(); DoCellH1(); DoCellI1(); DoCellJ1();
			DoCellA2(); DoCellB2(); DoCellC2(); DoCellD2(); DoCellE2(); DoCellF2(); DoCellG2(); DoCellH2(); DoCellI2(); DoCellJ2();
			DoCellA3(); DoCellB3(); DoCellC3(); DoCellD3(); DoCellE3(); DoCellF3(); DoCellG3(); DoCellH3(); DoCellI3(); DoCellJ3();
			DoCellA4(); DoCellB4(); DoCellC4(); DoCellD4(); DoCellE4(); DoCellF4(); DoCellG4(); DoCellH4(); DoCellI4(); DoCellJ4();
			DoCellA5(); DoCellB5(); DoCellC5(); DoCellD5(); DoCellE5(); DoCellF5(); DoCellG5(); DoCellH5(); DoCellI5(); DoCellJ5();
		}	
		
		VALUE DoCellA1() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellB1() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellC1() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellD1() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellE1() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellF1() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellG1() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellH1() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellI1() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellJ1() [1, +INF]
		MEETS {
			Idle();
		}
		
		
		VALUE DoCellA2() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellB2() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellC2() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellD2() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellE2() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellF2() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellG2() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellH2() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellI2() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellJ2() [1, +INF]
		MEETS {
			Idle();
		}
		
		
		VALUE DoCellA3() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellB3() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellC3() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellD3() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellE3() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellF3() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellG3() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellH3() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellI3() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellJ3() [1, +INF]
		MEETS {
			Idle();
		}
		
		
		VALUE DoCellA4() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellB4() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellC4() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellD4() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellE4() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellF4() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellG4() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellH4() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellI4() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellJ4() [1, +INF]
		MEETS {
			Idle();
		}
		
		
		VALUE DoCellA5() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellB5() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellC5() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellD5() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellE5() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellF5() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellG5() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellH5() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellI5() [1, +INF]
		MEETS {
			Idle();
		}
		
		VALUE DoCellJ5() [1, +INF]
		MEETS {
			Idle();
		}
	}
	
	COMP_TYPE SingletonStateVariable AgentBehaviorType(
		Idle(), _PickPlace(configurations, configurations, risk_levels))
	{
		VALUE Idle() [1, +INF]
		MEETS {
			_PickPlace(?start, ?goal, ?risk);
		}
		
		VALUE _PickPlace(?start, ?goal, ?risk) [1, 120]
		MEETS {
			Idle();	
		}
	}
	
	COMP_TYPE SingletonStateVariable SharableAreaType(Free(), Human(), Robot()) 
	{
		VALUE Free() [1, +INF]
		MEETS {
			Human();
			Robot();
		}
		
		VALUE Human() [1, +INF]
		MEETS {
			Free();
		}
		
		VALUE Robot() [1, +INF]
		MEETS {
			Free();
		}
	}
	
	COMPONENT Goal {FLEXIBLE hrc(functional)} : ProcessType;
	COMPONENT Mosaic {FLEXIBLE tasks(functional)} : MosaicProcessType;
	COMPONENT Assembly {FLEXIBLE coordination(functional)} : AssemblyProcessType;
	COMPONENT Human {FLEXIBLE operations(primitive)} : AgentBehaviorType;
	COMPONENT Robot {FLEXIBLE commands(primitive)} : AgentBehaviorType;
	COMPONENT BlueBoxArea {FLEXIBLE state(primitive)} : SharableAreaType;
	
	SYNCHRONIZE Goal.hrc
	{
		VALUE DoMosaic()
		{
			r1  Mosaic.tasks.DoRow1();
//			r2  Mosaic.tasks.DoRow2();
//			r3  Mosaic.tasks.DoRow3();
//			r4  Mosaic.tasks.DoRow4();
//			r5  Mosaic.tasks.DoRow5();
			
//			CONTAINS [0, +INF] [0, +INF] r1;
//			CONTAINS [0, +INF] [0, +INF] r2;
//			CONTAINS [0, +INF] [0, +INF] r3;
//			CONTAINS [0, +INF] [0, +INF] r4;
//			CONTAINS [0, +INF] [0, +INF] r5;
//			
//			r1 BEFORE [0, +INF] r3;
//			r1 BEFORE [0, +INF] r4;
//			r2 BEFORE [0, +INF] r4;
//			r3 BEFORE [0, +INF] r5;
//			r4 BEFORE [0, +INF] r5;
			
//			f0 Robot.commands.Idle();
//			f1 Human.operations.Idle();
//			
//			MET-BY f0;
//			MET-BY f1; 
		}
	}
	
	
	SYNCHRONIZE Mosaic.tasks
	{
		VALUE DoRow1()
		{
			t0 Assembly.coordination.DoCellA1();
			t1 Assembly.coordination.DoCellB1();
			t2 Assembly.coordination.DoCellC1();
			t3 Assembly.coordination.DoCellD1();
			t4 Assembly.coordination.DoCellE1();
			t5 Assembly.coordination.DoCellF1();
			t6 Assembly.coordination.DoCellG1();
			t7 Assembly.coordination.DoCellH1();
			t8 Assembly.coordination.DoCellI1();
			t9 Assembly.coordination.DoCellJ1();
			
			
			CONTAINS [0, +INF] [0, +INF] t0;
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
		}				
		
		VALUE DoRow2()
		{
			t0 Assembly.coordination.DoCellA2();
			t1 Assembly.coordination.DoCellB2();
			t2 Assembly.coordination.DoCellC2();
			t3 Assembly.coordination.DoCellD2();
			t4 Assembly.coordination.DoCellE2();
			t5 Assembly.coordination.DoCellF2();
			t6 Assembly.coordination.DoCellG2();
			t7 Assembly.coordination.DoCellH2();
			t8 Assembly.coordination.DoCellI2();
			t9 Assembly.coordination.DoCellJ2();
			
			
			CONTAINS [0, +INF] [0, +INF] t0;
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
		}
		
		VALUE DoRow3()
		{
			t0 Assembly.coordination.DoCellA3();
			t1 Assembly.coordination.DoCellB3();
			t2 Assembly.coordination.DoCellC3();
			t3 Assembly.coordination.DoCellD3();
			t4 Assembly.coordination.DoCellE3();
			t5 Assembly.coordination.DoCellF3();
			t6 Assembly.coordination.DoCellG3();
			t7 Assembly.coordination.DoCellH3();
			t8 Assembly.coordination.DoCellI3();
			t9 Assembly.coordination.DoCellJ3();
			
			
			CONTAINS [0, +INF] [0, +INF] t0;
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
		}
		
		VALUE DoRow4()
		{
			t0 Assembly.coordination.DoCellA4();
			t1 Assembly.coordination.DoCellB4();
			t2 Assembly.coordination.DoCellC4();
			t3 Assembly.coordination.DoCellD4();
			t4 Assembly.coordination.DoCellE4();
			t5 Assembly.coordination.DoCellF4();
			t6 Assembly.coordination.DoCellG4();
			t7 Assembly.coordination.DoCellH4();
			t8 Assembly.coordination.DoCellI4();
			t9 Assembly.coordination.DoCellJ4();
			
			
			CONTAINS [0, +INF] [0, +INF] t0;
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
		}
		
		VALUE DoRow5()
		{
			t0 Assembly.coordination.DoCellA5();
			t1 Assembly.coordination.DoCellB5();
			t2 Assembly.coordination.DoCellC5();
			t3 Assembly.coordination.DoCellD5();
			t4 Assembly.coordination.DoCellE5();
			t5 Assembly.coordination.DoCellF5();
			t6 Assembly.coordination.DoCellG5();
			t7 Assembly.coordination.DoCellH5();
			t8 Assembly.coordination.DoCellI5();
			t9 Assembly.coordination.DoCellJ5();
			
			
			CONTAINS [0, +INF] [0, +INF] t0;
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
		}
		
	}

	SYNCHRONIZE Assembly.coordination
	{
		// cells of row 1
		
		VALUE DoCellA1()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_orange;
			?goal = A1;
			?risk = 1;
		}
		
		VALUE DoCellB1()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_orange;
			?goal = B1;
			?risk = 1;
		}
		
		VALUE DoCellC1()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_orange;
			?goal = C1;
			?risk = 2;
		}
		
		// robot
		VALUE DoCellD1()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2	BlueBoxArea.state.Robot();
			
			
			CONTAINS [0, +INF] [0, +INF] t1;
			DURING [0, +INF] [0, +INF] t2;
				
			
			?start = box_blue;
			?goal = D1;
			?risk = 3;
		}
		
		// human
		VALUE DoCellD1()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			CONTAINS [0, +INF] [0, +INF] t1;
			DURING [0, +INF] [0, +INF] t2;	
			
			?start = box_blue;
			?goal = D1;
			?risk = 0;
		}
		
		VALUE DoCellE1()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = E1;
			?risk = 0;
		}
		
		
		
		// robot
		VALUE DoCellF1()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = F1;
			?risk = 3;
		}
		
		// human
		VALUE DoCellF1()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = F1;
			?risk = 0;
		}
		
		
		// robot
		VALUE DoCellG1()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = G1;
			?risk = 2;
		}
		
		
		
		// human
		VALUE DoCellG1()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = G1;
			?risk = 0;
		}
		
		
		// robot
		VALUE DoCellH1()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = H1;
			?risk = 3;
		}
		
		// human
		VALUE DoCellH1()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = H1;
			?risk = 0;
		}
		
		VALUE DoCellI1()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = I1;
			?risk = 0;
		}
		
		
		// robot
		VALUE DoCellJ1()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = J1;
			?risk = 3;
		}
		
		// human
		VALUE DoCellJ1()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = J1;
			?risk = 0;
			}
		
		
	
		// cells of row 2
		
		VALUE DoCellA2()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_orange;
			?goal = A2;
			?risk = 1;
		}
		
		// robot 
		VALUE DoCellB2()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = B2;
			?risk = 1;
		}
		
		// human
		VALUE DoCellB2()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = B2;
			?risk = 0;
		}
		
		// robot 
		VALUE DoCellC2()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = C2;
			?risk = 1;
		}
		
		// human
		VALUE DoCellC2()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = C2;
			?risk = 0;
		}
		
		
		// robot
		VALUE DoCellD2()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = D2;
			?risk = 3;
		}
		
		// human
		VALUE DoCellD2()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = D2;
			?risk = 0;
		}
		
		VALUE DoCellE2()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = E2;
			?risk = 0;
		}
		
		
		
		// robot
		VALUE DoCellF2()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = F2;
			?risk = 4;
		}
		
		// human
		VALUE DoCellF2()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = F2;
			?risk = 0;
		}
		
		// human
		VALUE DoCellG2()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = G2;
			?risk = 0;
		}
		
		
		// robot
		VALUE DoCellH2()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = H2;
			?risk = 4;
		}
		
		// human
		VALUE DoCellH2()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = H2;
			?risk = 0;
		}
		
		VALUE DoCellI2()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = I2;
			?risk = 0;
		}
		
		
		// robot
		VALUE DoCellJ2()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = J2;
			?risk = 3;
		}
		
		// human
		VALUE DoCellJ2()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = J2;
			?risk = 0;
		}
	
	
		// cells of row 3
	
	
		VALUE DoCellA3()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_orange;
			?goal = A3;
			?risk = 1;
		}
		
		VALUE DoCellB3()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_orange;
			?goal = B3;
			?risk = 1;
		}
		
		VALUE DoCellC3()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_orange;
			?goal = C3;
			?risk = 2;
		}
		
		// robot
		VALUE DoCellD3()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = D3;
			?risk = 3;
		}
		
		// human
		VALUE DoCellD3()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = D3;
			?risk = 0;
		}
		
		VALUE DoCellE3()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = E3;
			?risk = 0;
		}
		
		
		
		// robot
		VALUE DoCellF3()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = F3;
			?risk = 4;
		}
		
		// human
		VALUE DoCellF3()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = F3;
			?risk = 0;
		}
		
		// human
		VALUE DoCellG3()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = G3;
			?risk = 0;
		}
		
		
		// robot
		VALUE DoCellH3()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = H3;
			?risk = 4;
		}
		
		// human
		VALUE DoCellH3()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = H3;
			?risk = 0;
		}
		
		VALUE DoCellI3()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = I3;
			?risk = 0;
		}
		
		
		// robot
		VALUE DoCellJ3()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = J3;
			?risk = 3;
		}
		
		// human
		VALUE DoCellJ3()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = J3;
			?risk = 0;
		}
		
		
		// cells of row 4
		
		// robot 
		VALUE DoCellA4()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = A4;
			?risk = 1;
		}
		
		// human
		VALUE DoCellA4()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = A4;
			?risk = 0;
		}
		
		
		// robot 
		VALUE DoCellB4()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = B4;
			?risk = 1;
		}
		
		// human
		VALUE DoCellB4()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = B4;
			?risk = 0;
		}
		
		VALUE DoCellC4()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_orange;
			?goal = C4;
			?risk = 2;
		}
		
		// robot
		VALUE DoCellD4()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = D4;
			?risk = 3;
		}
		
		// human
		VALUE DoCellD4()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = D4;
			?risk = 0;
		}
		
		VALUE DoCellE4()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = E4;
			?risk = 0;
		}
		
		
		// human
		VALUE DoCellF4()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = F4;
			?risk = 0;
		}
		
		// robot
		VALUE DoCellG4()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = G4;
			?risk = 4;
		}
		
		// human
		VALUE DoCellG4()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = G4;
			?risk = 0;
		}
		
		
		VALUE DoCellH4()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = H4;
			?risk = 0;
		}
		
		VALUE DoCellI4()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = I4;
			?risk = 0;
		}
		
		
		// robot
		VALUE DoCellJ4()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = J4;
			?risk = 3;
		}
		
		// human
		VALUE DoCellJ4()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = J4;
			?risk = 0;
		}
		
		// cells of row 5
		
		
		VALUE DoCellA5()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_orange;
			?goal = A5;
			?risk = 1;
		}
		
		VALUE DoCellB5()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_orange;
			?goal = B5;
			?risk = 1;
		}
		
		VALUE DoCellC5()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_orange;
			?goal = C5;
			?risk = 2;
		}
		
		// robot
		VALUE DoCellD5()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = D5;
			?risk = 2;
		}
		
		// human
		VALUE DoCellD5()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = D5;
			?risk = 0;
		}
		
		// robot 
		VALUE DoCellE5()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = E5;
			?risk = 3;
		}
		
		// human 
		VALUE DoCellE5()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = E5;
			?risk = 0;
		}
		
		
		VALUE DoCellF5()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = F5;
			?risk = 0;
		}
		
		
		// robot
		VALUE DoCellG5()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = G5;
			?risk = 4;
		}
		
		// human
		VALUE DoCellG5()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = G5;
			?risk = 0;
		}
		
		VALUE DoCellH5()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_white;
			?goal = H5;
			?risk = 0;
		}
		
		// robot
		VALUE DoCellI5()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = I5;
			?risk = 3;
		}
		
		// human
		VALUE DoCellI5()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = I5;
			?risk = 0;
		}
		
		
		// robot
		VALUE DoCellJ5()
		{
			t1  Robot.commands._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Robot();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = J5;
			?risk = 3;
		}
		
		// human
		VALUE DoCellJ5()
		{
			t1  Human.operations._PickPlace(?start, ?goal, ?risk);
			t2 BlueBoxArea.state.Human();
			
			DURING [0, +INF] [0, +INF] t2; 
			
			CONTAINS [0, +INF] [0, +INF] t1;	
			
			?start = box_blue;
			?goal = J5;
			?risk = 0;
		}
		
	}
		
}
