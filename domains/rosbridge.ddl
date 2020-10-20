DOMAIN ROSBRIDGE
{
	TEMPORAL_MODULE temporal_module = [0, 100], 100;
	
	COMP_TYPE SingletonStateVariable DoType (
		Idle(), DoA(), DoB(), DoC(), DoD()
	)
	{
		VALUE Idle() [1, +INF]
		MEETS {
			DoA();
		}
		
		VALUE DoA() [6, 8]
		MEETS {
			DoB();
		}
		
		VALUE DoB() [7, 8]
		MEETS {
			DoC();
		}
		
		VALUE DoC() [3, 8]
		MEETS {
			DoD();
		}
		
		VALUE DoD() [5, 8]
		MEETS {
			Idle();
		} 
	}
	

	COMP_TYPE SingletonStateVariable GroundStationVisibilityType (_Visible(), _NotVisible())
	{
		VALUE _Visible() [1, +INF]
		MEETS {
			_NotVisible();
		}
		
		VALUE _NotVisible() [1, +INF]
		MEETS {
			_Visible();
		}
	}
	
	COMPONENT Do {FLEXIBLE something(primitive)} : DoType;
}
