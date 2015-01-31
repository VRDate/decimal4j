package ch.javasoft.decimal.op;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ch.javasoft.decimal.Decimal;
import ch.javasoft.decimal.arithmetic.DecimalArithmetics;
import ch.javasoft.decimal.scale.ScaleMetrics;
import ch.javasoft.decimal.test.TestSettings;
import ch.javasoft.decimal.truncate.OverflowMode;

/**
 * Unit test for {@link Decimal#divideToIntegralValue(Decimal)}
 */
@RunWith(Parameterized.class)
public class DivideToIntegralValueTest extends Abstract2DecimalArgsToDecimalResultTest {
	
	public DivideToIntegralValueTest(ScaleMetrics scaleMetrics, OverflowMode overflowMode, DecimalArithmetics arithmetics) {
		super(arithmetics);
	}

	@Parameters(name = "{index}: {0} {1}")
	public static Iterable<Object[]> data() {
		final List<Object[]> data = new ArrayList<Object[]>();
		for (final ScaleMetrics s : TestSettings.SCALES) {
			data.add(new Object[] {s, OverflowMode.UNCHECKED, s.getTruncatingArithmetics(OverflowMode.UNCHECKED)});
			data.add(new Object[] {s, OverflowMode.CHECKED, s.getTruncatingArithmetics(OverflowMode.CHECKED)});
		}
		return data;
	}
	
	@Override
	public void runSpecialValueTest() {
		super.runSpecialValueTest();
	}

	@Override
	protected String operation() {
		return "divideToIntegralValue";
	}
	
	@Override
	protected BigDecimal expectedResult(BigDecimal a, BigDecimal b) {
		return a.divideToIntegralValue(b, mathContextLong64);
	}
	
	@Override
	protected <S extends ScaleMetrics> Decimal<S> actualResult(Decimal<S> a, Decimal<S> b) {
		if (isUnchecked() && RND.nextBoolean()) {
			return a.divideToIntegralValue(b);
		}
		return a.divideToIntegralValue(b, getOverflowMode());
	}
}
