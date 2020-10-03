DOMAIN SHAREWORK_HRC_MOSAIC_V2
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
	
	
	COMP_TYPE SingletonStateVariable SharableAreaType(
		Free(), Robot(), Human()) 
	{
		VALUE Free() [1, +INF] 
		MEETS {
			Robot();
			Human();
		}
		
		VALUE Robot() [1, +INF]
		MEETS {
			Free();
		}
		
		VALUE Human() [1, +INF]
		MEETS {
			Free();
		}
	}
	
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
	
	COMPONENT Goal {FLEXIBLE hrc(functional)} : ProcessType;
	COMPONENT Mosaic {FLEXIBLE tasks(functional)} : MosaicProcessType;
	COMPONENT Human {FLEXIBLE operations(primitive)} : AgentBehaviorType;
	COMPONENT Robot {FLEXIBLE commands(primitive)} : AgentBehaviorType;
	COMPONENT BlueArea {FLEXIBLE shared(primitive)} : SharableAreaType;
	
	SYNCHRONIZE Goal.hrc
	{
		VALUE DoMosaic()
		{
			r1  Mosaic.tasks.DoRow1();
			r2  Mosaic.tasks.DoRow2();
			r3  Mosaic.tasks.DoRow3();
			r4  Mosaic.tasks.DoRow4();
			r5  Mosaic.tasks.DoRow5();
			
			CONTAINS [0, +INF] [0, +INF] r1;
			CONTAINS [0, +INF] [0, +INF] r2;
			CONTAINS [0, +INF] [0, +INF] r3;
			CONTAINS [0, +INF] [0, +INF] r4;
			CONTAINS [0, +INF] [0, +INF] r5;

			r1 BEFORE [0, +INF] r3;
			r1 BEFORE [0, +INF] r4;
			r2 BEFORE [0, +INF] r4;
			r3 BEFORE [0, +INF] r5;
			r4 BEFORE [0, +INF] r5;
		}
	}
	
	
	SYNCHRONIZE Mosaic.tasks
	{
		// row 1 - 3 Robot, 2 Human
		VALUE DoRow1()
		{
			t1  Robot.commands._PickPlace(?t1Start, ?t1Goal, ?t1Level);		// A1
			t2  Robot.commands._PickPlace(?t2Start, ?t2Goal, ?t2Level);		// B1
			t3  Robot.commands._PickPlace(?t3Start, ?t3Goal, ?t3Level);		// C1
			t4  Robot.commands._PickPlace(?t4Start, ?t4Goal, ?t4Level);		// D1
			t5  Human.operations._PickPlace(?t5Start, ?t5Goal, ?t5Level);	// E1
			t6  Robot.commands._PickPlace(?t6Start, ?t6Goal, ?t6Level);		// F1
			t7  Robot.commands._PickPlace(?t7Start, ?t7Goal, ?t7Level);		// G1
			t8  Human.operations._PickPlace(?t8Start, ?t8Goal, ?t8Level);	// H1
			t9  Human.operations._PickPlace(?t9Start, ?t9Goal, ?t9Level);	// I1
			t10  Human.operations._PickPlace(?t10Start, ?t10Goal, ?t10Level);// J1
			
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
			CONTAINS [0, +INF] [0, +INF] t10;
			
			?t1Start = box_orange;
			?t1Goal = A1;
			?t1Level = 1;
			
			?t2Start = box_orange;
			?t2Goal = B1;
			?t2Level = 1;
			
			?t3Start = box_orange;
			?t3Goal = C1;
			?t3Level = 2;
			
			?t4Start = box_blue;
			?t4Goal = D1;
			?t4Level = 3;
			
			?t5Start = box_white;
			?t5Goal = E1;
			?t5Level = 0;
			
			?t6Start = box_blue;
			?t6Goal = F1;
			?t6Level = 3;
						
			?t7Start = box_blue;
			?t7Goal = G1;
			?t7Level = 3;
			
			?t8Start = box_blue;
			?t8Goal = H1;
			?t8Level = 3;
			
			?t9Start = box_white;
			?t9Goal = I1;
			?t9Level = 0;
			
			
			?t10Start = box_blue;
			?t10Goal = J1;
			?t10Level = 3;
			
			
			// model shared space as mutually exclusive resource
			r1 BlueArea.shared.Robot();
			r2 BlueArea.shared.Human();
			
			t4 DURING [0, +INF] [0, +INF] r1;
			t6 DURING [0, +INF] [0, +INF] r1;
			t7 DURING [0, +INF] [0, +INF] r1;
			
			t8 DURING [0, +INF] [0, +INF] r2;
			t10 DURING [0, +INF] [0, +INF] r2;
		}
	
		// row 1 - 2 Robot, 3 Human
		VALUE DoRow1()
		{
			t1  Robot.commands._PickPlace(?t1Start, ?t1Goal, ?t1Level);		// A1
			t2  Robot.commands._PickPlace(?t2Start, ?t2Goal, ?t2Level);		// B1
			t3  Robot.commands._PickPlace(?t3Start, ?t3Goal, ?t3Level);		// C1
			t4  Robot.commands._PickPlace(?t4Start, ?t4Goal, ?t4Level);		// D1
			t5  Human.operations._PickPlace(?t5Start, ?t5Goal, ?t5Level);	// E1
			t6  Robot.commands._PickPlace(?t6Start, ?t6Goal, ?t6Level);		// F1
			t7  Human.operations._PickPlace(?t7Start, ?t7Goal, ?t7Level);	// G1
			t8  Human.operations._PickPlace(?t8Start, ?t8Goal, ?t8Level);	// H1
			t9  Human.operations._PickPlace(?t9Start, ?t9Goal, ?t9Level);	// I1
			t10  Human.operations._PickPlace(?t10Start, ?t10Goal, ?t10Level);// J1
			
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
			CONTAINS [0, +INF] [0, +INF] t10;
			
			
			?t1Start = box_orange;
			?t1Goal = A1;
			?t1Level = 1;
			
			?t2Start = box_orange;
			?t2Goal = B1;
			?t2Level = 1;
			
			?t3Start = box_orange;
			?t3Goal = C1;
			?t3Level = 2;
			
			?t4Start = box_blue;
			?t4Goal = D1;
			?t4Level = 3;
			
			?t5Start = box_white;
			?t5Goal = E1;
			?t5Level = 0;
			
			?t6Start = box_blue;
			?t6Goal = F1;
			?t6Level = 3;
						
			?t7Start = box_blue;
			?t7Goal = G1;
			?t7Level = 3;
			
			?t8Start = box_blue;
			?t8Goal = H1;
			?t8Level = 3;
			
			?t9Start = box_white;
			?t9Goal = I1;
			?t9Level = 0;
			
			
			?t10Start = box_blue;
			?t10Goal = J1;
			?t10Level = 3;
			
			// model shared space as mutually exclusive resource
			r1 BlueArea.shared.Robot();
			r2 BlueArea.shared.Human();
			
			t4 DURING [0, +INF] [0, +INF] r1;
			t6 DURING [0, +INF] [0, +INF] r1;
			
			t7 DURING [0, +INF] [0, +INF] r2;
			t8 DURING [0, +INF] [0, +INF] r2;
			t10 DURING [0, +INF] [0, +INF] r2;
		}
		
		// row 2 - 4 Robot, 2 Human
		VALUE DoRow2()
		{
			t1  Robot.commands._PickPlace(?t1Start, ?t1Goal, ?t1Level);		// A1
			t2  Robot.commands._PickPlace(?t2Start, ?t2Goal, ?t2Level);		// B1
			t3  Robot.commands._PickPlace(?t3Start, ?t3Goal, ?t3Level);		// C1
			t4  Robot.commands._PickPlace(?t4Start, ?t4Goal, ?t4Level);		// D1
			t5  Human.operations._PickPlace(?t5Start, ?t5Goal, ?t5Level);	// E1
			t6  Robot.commands._PickPlace(?t6Start, ?t6Goal, ?t6Level);		// F1
			t7  Human.operations._PickPlace(?t7Start, ?t7Goal, ?t7Level);		// G1
			t8  Human.operations._PickPlace(?t8Start, ?t8Goal, ?t8Level);		// H1
			t9  Human.operations._PickPlace(?t9Start, ?t9Goal, ?t9Level);	// I1
			t10  Human.operations._PickPlace(?t10Start, ?t10Goal, ?t10Level);	// J1
			
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
			CONTAINS [0, +INF] [0, +INF] t10;
			
			
			?t1Start = box_orange;
			?t1Goal = A2;
			?t1Level = 1;
			
			?t2Start = box_blue;
			?t2Goal = B2;
			?t2Level = 2;
			
			?t3Start = box_blue;
			?t3Goal = C2;
			?t3Level = 2;
			
			?t4Start = box_blue;
			?t4Goal = D2;
			?t4Level = 3;
			
			?t5Start = box_white;
			?t5Goal = E2;
			?t5Level = 0;
			
			?t6Start = box_blue;
			?t6Goal = F2;
			?t6Level = 4;
						
			?t7Start = box_white;
			?t7Goal = G2;
			?t7Level = 0;
			
			?t8Start = box_blue;
			?t8Goal = H2;
			?t8Level = 4;
			
			?t9Start = box_white;
			?t9Goal = I2;
			?t9Level = 0;
			
			
			?t10Start = box_blue;
			?t10Goal = J2;
			?t10Level = 3;
			
			
			// model shared space as mutually exclusive resource
			r1 BlueArea.shared.Robot();
			r2 BlueArea.shared.Human();
			
			t2 DURING [0, +INF] [0, +INF] r1;
			t8 DURING [0, +INF] [0, +INF] r2;
			
			t2 BEFORE [0, +INF] t8;
			t3 BEFORE [0, +INF] t8;
			t4 BEFORE [0, +INF] t8;
			t6 BEFORE [0, +INF] t8;
			
			t2 BEFORE [0, +INF] t10;
			t3 BEFORE [0, +INF] t10;
			t4 BEFORE [0, +INF] t10;
			t6 BEFORE [0, +INF] t10;
		}
	
		// row 2 - 3 Robot, 3 Human
		VALUE DoRow2()
		{
			t1  Robot.commands._PickPlace(?t1Start, ?t1Goal, ?t1Level);		// A1
			t2  Robot.commands._PickPlace(?t2Start, ?t2Goal, ?t2Level);		// B1
			t3  Robot.commands._PickPlace(?t3Start, ?t3Goal, ?t3Level);		// C1
			t4  Robot.commands._PickPlace(?t4Start, ?t4Goal, ?t4Level);		// D1
			t5  Human.operations._PickPlace(?t5Start, ?t5Goal, ?t5Level);	// E1
			t6  Human.operations._PickPlace(?t6Start, ?t6Goal, ?t6Level);		// F1
			t7  Human.operations._PickPlace(?t7Start, ?t7Goal, ?t7Level);		// G1
			t8  Human.operations._PickPlace(?t8Start, ?t8Goal, ?t8Level);		// H1
			t9  Human.operations._PickPlace(?t9Start, ?t9Goal, ?t9Level);	// I1
			t10  Human.operations._PickPlace(?t10Start, ?t10Goal, ?t10Level);	// J1
			
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
			CONTAINS [0, +INF] [0, +INF] t10;
			
			
			?t1Start = box_orange;
			?t1Goal = A2;
			?t1Level = 1;
			
			?t2Start = box_blue;
			?t2Goal = B2;
			?t2Level = 2;
			
			?t3Start = box_blue;
			?t3Goal = C2;
			?t3Level = 2;
			
			?t4Start = box_blue;
			?t4Goal = D2;
			?t4Level = 3;
			
			?t5Start = box_white;
			?t5Goal = E2;
			?t5Level = 0;
			
			?t6Start = box_blue;
			?t6Goal = F2;
			?t6Level = 4;
						
			?t7Start = box_white;
			?t7Goal = G2;
			?t7Level = 0;
			
			?t8Start = box_blue;
			?t8Goal = H2;
			?t8Level = 4;
			
			?t9Start = box_white;
			?t9Goal = I2;
			?t9Level = 0;
			
			
			?t10Start = box_blue;
			?t10Goal = J2;
			?t10Level = 3;
			
			// model shared space as mutually exclusive resource
			r1 BlueArea.shared.Robot();
			r2 BlueArea.shared.Human();
			
			t2 DURING [0, +INF] [0, +INF] r1;
			t3 DURING [0, +INF] [0, +INF] r1;
			t4 DURING [0, +INF] [0, +INF] r1;
			
			t6 DURING [0, +INF] [0, +INF] r2;
			t8 DURING [0, +INF] [0, +INF] r2;
			t10 DURING [0, +INF] [0, +INF] r2;
			
			t2 BEFORE [0, +INF] t6;
			t3 BEFORE [0, +INF] t6;
			t4 BEFORE [0, +INF] t6;
			
			
			t2 BEFORE [0, +INF] t8;
			t3 BEFORE [0, +INF] t8;
			t4 BEFORE [0, +INF] t8;
			
			t2 BEFORE [0, +INF] t10;
			t3 BEFORE [0, +INF] t10;
			t4 BEFORE [0, +INF] t10;
		}
		
		// row 2 - 2 Robot, 4 Human
		VALUE DoRow2()
		{
			t1  Robot.commands._PickPlace(?t1Start, ?t1Goal, ?t1Level);		// A1
			t2  Robot.commands._PickPlace(?t2Start, ?t2Goal, ?t2Level);		// B1
			t3  Robot.commands._PickPlace(?t3Start, ?t3Goal, ?t3Level);		// C1
			t4  Human.operations._PickPlace(?t4Start, ?t4Goal, ?t4Level);		// D1
			t5  Human.operations._PickPlace(?t5Start, ?t5Goal, ?t5Level);	// E1
			t6  Human.operations._PickPlace(?t6Start, ?t6Goal, ?t6Level);		// F1
			t7  Human.operations._PickPlace(?t7Start, ?t7Goal, ?t7Level);		// G1
			t8  Human.operations._PickPlace(?t8Start, ?t8Goal, ?t8Level);		// H1
			t9  Human.operations._PickPlace(?t9Start, ?t9Goal, ?t9Level);	// I1
			t10  Human.operations._PickPlace(?t10Start, ?t10Goal, ?t10Level);	// J1
			
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
			CONTAINS [0, +INF] [0, +INF] t10;
			
			
			?t1Start = box_orange;
			?t1Goal = A2;
			?t1Level = 1;
			
			?t2Start = box_blue;
			?t2Goal = B2;
			?t2Level = 2;
			
			?t3Start = box_blue;
			?t3Goal = C2;
			?t3Level = 2;
			
			?t4Start = box_blue;
			?t4Goal = D2;
			?t4Level = 3;
			
			?t5Start = box_white;
			?t5Goal = E2;
			?t5Level = 0;
			
			?t6Start = box_blue;
			?t6Goal = F2;
			?t6Level = 4;
						
			?t7Start = box_white;
			?t7Goal = G2;
			?t7Level = 0;
			
			?t8Start = box_blue;
			?t8Goal = H2;
			?t8Level = 4;
			
			?t9Start = box_white;
			?t9Goal = I2;
			?t9Level = 0;
			
			
			?t10Start = box_blue;
			?t10Goal = J2;
			?t10Level = 3;
			
			// model shared space as mutually exclusive resource
			r1 BlueArea.shared.Robot();
			r2 BlueArea.shared.Human();
			
			t2 DURING [0, +INF] [0, +INF] r1;
			t3 DURING [0, +INF] [0, +INF] r1;
			
			t4 DURING [0, +INF] [0, +INF] r2;
			t6 DURING [0, +INF] [0, +INF] r2;
			t8 DURING [0, +INF] [0, +INF] r2;
			t10 DURING [0, +INF] [0, +INF] r2;
			
			
			t2 BEFORE [0, +INF] t4;
			t3 BEFORE [0, +INF] t4;
			
			t2 BEFORE [0, +INF] t6;
			t3 BEFORE [0, +INF] t6;
			
			t2 BEFORE [0, +INF] t8;
			t3 BEFORE [0, +INF] t8;
			
			
			t2 BEFORE [0, +INF] t10;
			t3 BEFORE [0, +INF] t10;
		}
		
		// row 3 - 2 Robot, 2 Human
		VALUE DoRow3()
		{
			t1  Robot.commands._PickPlace(?t1Start, ?t1Goal, ?t1Level);		// A1
			t2  Robot.commands._PickPlace(?t2Start, ?t2Goal, ?t2Level);		// B1
			t3  Robot.commands._PickPlace(?t3Start, ?t3Goal, ?t3Level);		// C1
			t4  Robot.commands._PickPlace(?t4Start, ?t4Goal, ?t4Level);		// D1
			t5  Human.operations._PickPlace(?t5Start, ?t5Goal, ?t5Level);	// E1
			t6  Robot.commands._PickPlace(?t6Start, ?t6Goal, ?t6Level);		// F1
			t7  Human.operations._PickPlace(?t7Start, ?t7Goal, ?t7Level);		// G1
			t8  Human.operations._PickPlace(?t8Start, ?t8Goal, ?t8Level);		// H1
			t9  Human.operations._PickPlace(?t9Start, ?t9Goal, ?t9Level);	// I1
			t10  Human.operations._PickPlace(?t10Start, ?t10Goal, ?t10Level);	// J1
			
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
			CONTAINS [0, +INF] [0, +INF] t10;
			
			
			?t1Start = box_orange;
			?t1Goal = A3;
			?t1Level = 1;
			
			?t2Start = box_orange;
			?t2Goal = B3;
			?t2Level = 1;
			
			?t3Start = box_orange;
			?t3Goal = C3;
			?t3Level = 1;
			
			?t4Start = box_blue;
			?t4Goal = D3;
			?t4Level = 2;
			
			?t5Start = box_white;
			?t5Goal = E3;
			?t5Level = 0;
			
			?t6Start = box_blue;
			?t6Goal = F3;
			?t6Level = 4;
						
			?t7Start = box_white;
			?t7Goal = G3;
			?t7Level = 0;
			
			?t8Start = box_blue;
			?t8Goal = H3;
			?t8Level = 4;
			
			?t9Start = box_white;
			?t9Goal = I3;
			?t9Level = 0;
			
			
			?t10Start = box_blue;
			?t10Goal = J3;
			?t10Level = 3;
			
			
			// model shared space as mutually exclusive resource
			r1 BlueArea.shared.Robot();
			r2 BlueArea.shared.Human();
			
			t4 DURING [0, +INF] [0, +INF] r1;
			t6 DURING [0, +INF] [0, +INF] r1;
			
			t8 DURING [0, +INF] [0, +INF] r2;
			t10 DURING [0, +INF] [0, +INF] r2;
			
			t4 BEFORE [0, +INF] t8;
			t6 BEFORE [0, +INF] t8;
			
			t4 BEFORE [0, +INF] t10;
			t6 BEFORE [0, +INF] t10;
		}
		
		// row 4 - 3 Robot, 4 Human
		VALUE DoRow4()
		{
			t1  Robot.commands._PickPlace(?t1Start, ?t1Goal, ?t1Level);		// A1
			t2  Robot.commands._PickPlace(?t2Start, ?t2Goal, ?t2Level);		// B1
			t3  Robot.commands._PickPlace(?t3Start, ?t3Goal, ?t3Level);		// C1
			t4  Robot.commands._PickPlace(?t4Start, ?t4Goal, ?t4Level);		// D1
			t5  Human.operations._PickPlace(?t5Start, ?t5Goal, ?t5Level);	// E1
			t6  Human.operations._PickPlace(?t6Start, ?t6Goal, ?t6Level);		// F1
			t7  Human.operations._PickPlace(?t7Start, ?t7Goal, ?t7Level);		// G1
			t8  Human.operations._PickPlace(?t8Start, ?t8Goal, ?t8Level);		// H1
			t9  Human.operations._PickPlace(?t9Start, ?t9Goal, ?t9Level);		// I1
			t10  Human.operations._PickPlace(?t10Start, ?t10Goal, ?t10Level);	// J1
			
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
			CONTAINS [0, +INF] [0, +INF] t10;
			
			
			?t1Start = box_blue;
			?t1Goal = A4;
			?t1Level = 2;
			
			?t2Start = box_blue;
			?t2Goal = B4;
			?t2Level = 3;
			
			?t3Start = box_orange;
			?t3Goal = C4;
			?t3Level = 3;
			
			?t4Start = box_blue;
			?t4Goal = D4;
			?t4Level = 3;
			
			?t5Start = box_blue;
			?t5Goal = E4;
			?t5Level = 0;
			
			?t6Start = box_white;
			?t6Goal = F4;
			?t6Level = 0;
						
			?t7Start = box_blue;
			?t7Goal = G4;
			?t7Level = 4;
			
			?t8Start = box_white;
			?t8Goal = H4;
			?t8Level = 0;
			
			?t9Start = box_blue;
			?t9Goal = I4;
			?t9Level = 0;
			
			
			?t10Start = box_blue;
			?t10Goal = J4;
			?t10Level = 3;
			
			// model shared space as mutually exclusive resource
			r1 BlueArea.shared.Robot();
			r2 BlueArea.shared.Human();
			
			t1 DURING [0, +INF] [0, +INF] r1;
			t2 DURING [0, +INF] [0, +INF] r1;
			t4 DURING [0, +INF] [0, +INF] r1;
			
			t5 DURING [0, +INF] [0, +INF] r2;
			t7 DURING [0, +INF] [0, +INF] r2;
			t9 DURING [0, +INF] [0, +INF] r2;
			t10 DURING [0, +INF] [0, +INF] r2;
			
			
			t1 BEFORE [0, +INF] t5;
			t2 BEFORE [0, +INF] t5;
			t4 BEFORE [0, +INF] t5;
			
			t1 BEFORE [0, +INF] t7;
			t2 BEFORE [0, +INF] t7;
			t4 BEFORE [0, +INF] t7;
			
			t1 BEFORE [0, +INF] t9;
			t2 BEFORE [0, +INF] t9;
			t4 BEFORE [0, +INF] t9;
			
			t1 BEFORE [0, +INF] t10;
			t2 BEFORE [0, +INF] t10;
			t4 BEFORE [0, +INF] t10;
		}

		// row 5 - 3 Robot, 2 Human
		VALUE DoRow5()
		{
			t1  Robot.commands._PickPlace(?t1Start, ?t1Goal, ?t1Level);		// A1
			t2  Robot.commands._PickPlace(?t2Start, ?t2Goal, ?t2Level);		// B1
			t3  Robot.commands._PickPlace(?t3Start, ?t3Goal, ?t3Level);		// C1
			t4  Robot.commands._PickPlace(?t4Start, ?t4Goal, ?t4Level);		// D1
			t5  Robot.commands._PickPlace(?t5Start, ?t5Goal, ?t5Level);		// E1
			t6  Human.operations._PickPlace(?t6Start, ?t6Goal, ?t6Level);		// F1
			t7  Robot.commands._PickPlace(?t7Start, ?t7Goal, ?t7Level);		// G1
			t8  Human.operations._PickPlace(?t8Start, ?t8Goal, ?t8Level);		// H1
			t9  Human.operations._PickPlace(?t9Start, ?t9Goal, ?t9Level);	// I1
			t10  Human.operations._PickPlace(?t10Start, ?t10Goal, ?t10Level);	// J1
			
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
			CONTAINS [0, +INF] [0, +INF] t10;
			
			
			?t1Start = box_orange;
			?t1Goal = A5;
			?t1Level = 1;
			
			?t2Start = box_orange;
			?t2Goal = B5;
			?t2Level = 1;
			
			?t3Start = box_orange;
			?t3Goal = C5;
			?t3Level = 2;
			
			?t4Start = box_blue;
			?t4Goal = D5;
			?t4Level = 2;
			
			?t5Start = box_blue;
			?t5Goal = E5;
			?t5Level = 3;
			
			?t6Start = box_white;
			?t6Goal = F5;
			?t6Level = 0;
						
			?t7Start = box_blue;
			?t7Goal = G5;
			?t7Level = 4;
			
			?t8Start = box_white;
			?t8Goal = H5;
			?t8Level = 0;
			
			?t9Start = box_blue;
			?t9Goal = I5;
			?t9Level = 3;
			
			
			?t10Start = box_blue;
			?t10Goal = J5;
			?t10Level = 1;
			
			
			// model shared space as mutually exclusive resource
			r1 BlueArea.shared.Robot();
			r2 BlueArea.shared.Human();
			
			t4 DURING [0, +INF] [0, +INF] r1;
			t5 DURING [0, +INF] [0, +INF] r1;
			t7 DURING [0, +INF] [0, +INF] r1;
			
			t9 DURING [0, +INF] [0, +INF] r2;
			t10 DURING [0, +INF] [0, +INF] r2;
			
			t4 BEFORE [0, +INF] t9;
			t5 BEFORE [0, +INF] t9;
			t7 BEFORE [0, +INF] t9;
			
			t4 BEFORE [0, +INF] t10;
			t5 BEFORE [0, +INF] t10;
			t7 BEFORE [0, +INF] t10;
		}
		
		// row 5 - 2 Robot, 3 Human
		VALUE DoRow5()
		{
			t1  Robot.commands._PickPlace(?t1Start, ?t1Goal, ?t1Level);		// A1
			t2  Robot.commands._PickPlace(?t2Start, ?t2Goal, ?t2Level);		// B1
			t3  Robot.commands._PickPlace(?t3Start, ?t3Goal, ?t3Level);		// C1
			t4  Robot.commands._PickPlace(?t4Start, ?t4Goal, ?t4Level);		// D1
			t5  Robot.commands._PickPlace(?t5Start, ?t5Goal, ?t5Level);		// E1
			t6  Human.operations._PickPlace(?t6Start, ?t6Goal, ?t6Level);		// F1
			t7  Human.operations._PickPlace(?t7Start, ?t7Goal, ?t7Level);		// G1
			t8  Human.operations._PickPlace(?t8Start, ?t8Goal, ?t8Level);		// H1
			t9  Human.operations._PickPlace(?t9Start, ?t9Goal, ?t9Level);	// I1
			t10  Human.operations._PickPlace(?t10Start, ?t10Goal, ?t10Level);	// J1
			
			CONTAINS [0, +INF] [0, +INF] t1;
			CONTAINS [0, +INF] [0, +INF] t2;
			CONTAINS [0, +INF] [0, +INF] t3;
			CONTAINS [0, +INF] [0, +INF] t4;
			CONTAINS [0, +INF] [0, +INF] t5;
			CONTAINS [0, +INF] [0, +INF] t6;
			CONTAINS [0, +INF] [0, +INF] t7;
			CONTAINS [0, +INF] [0, +INF] t8;
			CONTAINS [0, +INF] [0, +INF] t9;
			CONTAINS [0, +INF] [0, +INF] t10;
			
			
			?t1Start = box_orange;
			?t1Goal = A5;
			?t1Level = 1;
			
			?t2Start = box_orange;
			?t2Goal = B5;
			?t2Level = 1;
			
			?t3Start = box_orange;
			?t3Goal = C5;
			?t3Level = 2;
			
			?t4Start = box_blue;
			?t4Goal = D5;
			?t4Level = 2;
			
			?t5Start = box_blue;
			?t5Goal = E5;
			?t5Level = 3;
			
			?t6Start = box_white;
			?t6Goal = F5;
			?t6Level = 0;
						
			?t7Start = box_blue;
			?t7Goal = G5;
			?t7Level = 4;
			
			?t8Start = box_white;
			?t8Goal = H5;
			?t8Level = 0;
			
			?t9Start = box_blue;
			?t9Goal = I5;
			?t9Level = 3;
			
			
			?t10Start = box_blue;
			?t10Goal = J5;
			?t10Level = 1;
			
			// model shared space as mutually exclusive resource
			r1 BlueArea.shared.Robot();
			r2 BlueArea.shared.Human();
			
			t4 DURING [0, +INF] [0, +INF] r1;
			t5 DURING [0, +INF] [0, +INF] r1;
			
			t7 DURING [0, +INF] [0, +INF] r2;
			t9 DURING [0, +INF] [0, +INF] r2;
			t10 DURING [0, +INF] [0, +INF] r2;
			
			t4 BEFORE [0, +INF] t7;
			t5 BEFORE [0, +INF] t7;
			
			t4 BEFORE [0, +INF] t9;
			t5 BEFORE [0, +INF] t9;
			
			t4 BEFORE [0, +INF] t10;
			t5 BEFORE [0, +INF] t10;
		}
	
	}
}
