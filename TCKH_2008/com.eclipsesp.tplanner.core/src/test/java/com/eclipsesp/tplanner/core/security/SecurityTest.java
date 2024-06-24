package com.eclipsesp.tplanner.core.security;

import org.junit.*;

import com.eclipsesp.tplanner.core.security.utils.*;

/**
 * Testing unit for security utilities
 * 
 * @author akrumin
 */
public class SecurityTest {

	@Test
	public void compileSecurityDataTest() {
		int permissionData[] = { 0, 16, 48, 80, 112, 240, 1, 3, 5, 7, 15 };
		IMask mask[][] =
		        {
		                { SinglePermission.CAN_NOTHING,
		                        SinglePermission.CAN_NOTHING },
		                { SinglePermission.CAN_READ_SELF,
		                        SinglePermission.CAN_NOTHING },
		                { SinglePermission.CAN_WRITE_SELF,
		                        SinglePermission.CAN_NOTHING },
		                { SinglePermission.CAN_READ_ALL,
		                        SinglePermission.CAN_NOTHING },
		                { SinglePermission.CAN_READ_ALL_WRITE_SELF,
		                        SinglePermission.CAN_NOTHING },
		                { SinglePermission.CAN_WRITE_ALL,
		                        SinglePermission.CAN_NOTHING },
		                { SinglePermission.CAN_NOTHING,
		                        SinglePermission.CAN_READ_SELF },
		                { SinglePermission.CAN_NOTHING,
		                        SinglePermission.CAN_WRITE_SELF },
		                { SinglePermission.CAN_NOTHING,
		                        SinglePermission.CAN_READ_ALL },
		                { SinglePermission.CAN_NOTHING,
		                        SinglePermission.CAN_READ_ALL_WRITE_SELF },
		                { SinglePermission.CAN_NOTHING,
		                        SinglePermission.CAN_WRITE_ALL } };
		for (int t = 0; t < 10; t++) {
			Assert.assertTrue(permissionData[t] == Security.compileSecurityData(
			        (SinglePermission) mask[t][0],
			        (SinglePermission) mask[t][1]));
		}
	}

	@Test
	public void checkPermissionMaskTest() {
		int mask =
		        Security.compileSecurityMask(SingleMask.CAN_NOTHING,
		                SingleMask.CAN_NOTHING);
		int data =
		        Security.compileSecurityData(SinglePermission.CAN_WRITE_ALL,
		                SinglePermission.CAN_READ_ALL_WRITE_SELF);
		Assert.assertTrue(Security.checkPermissionMask(data, mask));
	}

	@Test
	public void checkcheckByPatternTest() {

		Assert.assertTrue(Security.checkByPattern("aS.d@as.D.com",
		        Security.EMAILPATTERN));
		Assert.assertTrue(Security.checkByPattern("asd_12S@#asd",
		        Security.PASSPATTERN));
	}

	@Ignore("not necessary")
	@Test
	public void encodePassTest() {
		System.out.print(Security.encodePass("asdasdasa"));
		Assert.assertTrue(true);
	}

	@Ignore("not necessary")
	@Test
	public void promoCodeTest() {
		String value = Security.encodePromoCode("asd@asd.com");
		System.out.print(value);
		System.out.print(Security.decodePromoCode(value));
		Assert.assertTrue(true);
	}
}
