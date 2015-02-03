package org.decimal4j.op;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.decimal4j.api.Decimal;
import org.decimal4j.api.DecimalArithmetic;
import org.decimal4j.scale.ScaleMetrics;
import org.decimal4j.test.TestSettings;
import org.decimal4j.truncate.OverflowMode;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Unit test for {@link Decimal#subtract(Decimal)}
 */
@RunWith(Parameterized.class)
public class SubtractTest extends Abstract2DecimalArgsToDecimalResultTest {
	
	public SubtractTest(ScaleMetrics scaleMetrics, OverflowMode overflowMode, DecimalArithmetic arithmetic) {
		super(arithmetic);
	}

	@Parameters(name = "{index}: {0}, {1}")
	public static Iterable<Object[]> data() {
		final List<Object[]> data = new ArrayList<Object[]>();
		for (final ScaleMetrics s : TestSettings.SCALES) {
			data.add(new Object[] {s, OverflowMode.UNCHECKED, s.getTruncatingArithmetic(OverflowMode.UNCHECKED)});
			data.add(new Object[] {s, OverflowMode.CHECKED, s.getTruncatingArithmetic(OverflowMode.CHECKED)});
		}
		return data;
	}

	@Override
	protected String operation() {
		return "-";
	}
	
	@Override
	protected BigDecimal expectedResult(BigDecimal a, BigDecimal b) {
		return a.subtract(b);
	}
	
	@Override
	protected <S extends ScaleMetrics> Decimal<S> actualResult(Decimal<S> a, Decimal<S> b) {
		if (isUnchecked() && RND.nextBoolean()) {
			return a.subtract(b);
		}
		return a.subtract(b, getTruncationPolicy());
	}
}
