package org.decimal4j.op;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.decimal4j.api.Decimal;
import org.decimal4j.api.DecimalArithmetic;
import org.decimal4j.scale.ScaleMetrics;
import org.decimal4j.test.TestSettings;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Unit test for {@link Decimal#avg(Decimal)} and {@link Decimal#avg(Decimal, RoundingMode)}
 */
@RunWith(Parameterized.class)
public class AvgTest extends Abstract2DecimalArgsToDecimalResultTest {
	
	private static final BigDecimal TWO = BigDecimal.valueOf(2);
	
	public AvgTest(ScaleMetrics scaleMetrics, RoundingMode roundingMode, DecimalArithmetic arithmetic) {
		super(arithmetic);
	}

	@Parameters(name = "{index}: {0}, {1}")
	public static Iterable<Object[]> data() {
		final List<Object[]> data = new ArrayList<Object[]>();
		for (final ScaleMetrics s : TestSettings.SCALES) {
			for (final RoundingMode rm : TestSettings.UNCHECKED_ROUNDING_MODES) {
				data.add(new Object[] {s, rm, s.getArithmetic(rm)});
			}
		}
		return data;
	}
	
	@Override
	protected String operation() {
		return "avg";
	}
	
	@Override
	protected BigDecimal expectedResult(BigDecimal a, BigDecimal b) {
		return a.add(b).divide(TWO, getRoundingMode());
	}
	
	@Override
	protected <S extends ScaleMetrics> Decimal<S> actualResult(Decimal<S> a, Decimal<S> b) {
		if (isStandardTruncationPolicy() && RND.nextBoolean()) {
			return a.avg(b);
		} else {
			return a.avg(b, getRoundingMode());
		}
	}
}
