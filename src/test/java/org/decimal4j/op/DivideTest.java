package org.decimal4j.op;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.decimal4j.api.Decimal;
import org.decimal4j.api.DecimalArithmetic;
import org.decimal4j.scale.ScaleMetrics;
import org.decimal4j.test.TestSettings;
import org.decimal4j.truncate.TruncationPolicy;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Unit test for {@link Decimal#divide(Decimal, RoundingMode)}
 */
@RunWith(Parameterized.class)
public class DivideTest extends Abstract2DecimalArgsToDecimalResultTest {
	
	public DivideTest(ScaleMetrics scaleMetrics, TruncationPolicy truncationPolicy, DecimalArithmetic arithmetic) {
		super(arithmetic);
	}

	@Parameters(name = "{index}: {0}, {1}")
	public static Iterable<Object[]> data() {
		final List<Object[]> data = new ArrayList<Object[]>();
		for (final ScaleMetrics s : TestSettings.SCALES) {
			for (final TruncationPolicy tp : TestSettings.POLICIES) {
				final DecimalArithmetic arith = s.getArithmetic(tp);
				data.add(new Object[] {s, tp, arith});
			}
		}
		return data;
	}
	
	@Override
	public void runSpecialValueTest() {
		super.runSpecialValueTest();
	}

	@Override
	protected String operation() {
		return "/";
	}
	
	@Override
	protected BigDecimal expectedResult(BigDecimal a, BigDecimal b) {
		return a.divide(b, mathContextLong128);
	}
	
	@Override
	protected <S extends ScaleMetrics> Decimal<S> actualResult(Decimal<S> a, Decimal<S> b) {
		if (isStandardTruncationPolicy() && RND.nextBoolean()) {
			return a.divide(b);
		} else {
			if (isUnchecked() && RND.nextBoolean()) {
				return a.divide(b, getRoundingMode());
			} else {
				return a.divide(b, getTruncationPolicy());
			}
		}
	}
}
