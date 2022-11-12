package lotto.view;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	private static final String INPUT_USER_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
	private static final String DEFAULT_ERROR_MESSAGE = "[ERROR] ";
	private static final String INPUT_MONEY_ERROR_MESSAGE = "구입금액은 숫자여야 합니다.";
	private static final String VALID_MONEY_ERROR_MESSAGE = "구입 금액은 1000원 단위로 입력하셔야 합니다.";
	private static final String INPUT_WINNING_NUMBER_MESSAGE = "당첨 번호를 입력해 주세요.";
	private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해주세요";
	private static final String VALID_WIN_NUMBER_MESSAGE = "당첨 번호는 1~45의 범위를 가지는 6개의 숫자여야 합니다.";
	private static final String VALID_BONUS_NUMBER_MESSAGE = "보너스 번호는 1~45의 범위를 가지는 1개의 숫자여야 합니다.";
	private static final String INPUT_WIN_NUMBER_DIVIDE_STRING = ",";
	private static final int DIVIDE_UNIT = 1000;
	private static final int ZERO_NUMBER = 0;
	private static final int DEFAULT_SIZE = 6;
	private static final int MAX_VALUE = 45;
	private static final int MIN_VALUE = 1;

	public int inputUserMoney() {
		System.out.println(INPUT_USER_MONEY_MESSAGE);
		int money = 0;
		try {
			money = Integer.parseInt(Console.readLine());
		} catch (IllegalArgumentException e) {
			System.out.println(DEFAULT_ERROR_MESSAGE + INPUT_MONEY_ERROR_MESSAGE);
		}
		return money;
	}

	public void validMoney(int money) {
		if (isNotDividedInto1000Units(money)) {
			throw new IllegalArgumentException(DEFAULT_ERROR_MESSAGE + VALID_MONEY_ERROR_MESSAGE);
		}
	}

	private boolean isNotDividedInto1000Units (int money) {
		return money % DIVIDE_UNIT != ZERO_NUMBER;
	}

	public String inputWinningNumber() {
		System.out.println();
		System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
		return Console.readLine();
	}

	public String inputBonusNumber() {
		System.out.println();
		System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
		return Console.readLine();
	}

	public void validBonusNumber(String str) {
		if (isWrongRangeBonusNumber(str)) {
			throw new IllegalArgumentException(DEFAULT_ERROR_MESSAGE + VALID_BONUS_NUMBER_MESSAGE);
		}
	}

	private boolean isWrongRangeBonusNumber(String str) {
		int bonusNumber = Integer.parseInt(str);
		return !(MIN_VALUE <= bonusNumber && bonusNumber <= MAX_VALUE);
	}

	public void validWinningNumber(String userInput) {
		if (isWrongSize(userInput) || isDuplicateNumber(userInput)
			|| isWrongRangeWinningNumber(userInput)) {
			throw new IllegalArgumentException(DEFAULT_ERROR_MESSAGE + VALID_WIN_NUMBER_MESSAGE);
		}
	}

	private boolean isDuplicateNumber(String str) {
		Set<String> notDuplicateNumbers = Arrays.stream(str.split(INPUT_WIN_NUMBER_DIVIDE_STRING)).collect(Collectors.toSet());
		return notDuplicateNumbers.size() != DEFAULT_SIZE;
	}

	private boolean isWrongRangeWinningNumber(String str) {
		return Arrays.stream(str.split(INPUT_WIN_NUMBER_DIVIDE_STRING))
			.mapToInt(Integer::parseInt).noneMatch(num -> MIN_VALUE <= num && num <= MAX_VALUE);
	}

	private boolean isWrongSize(String str) {
		return str.split(INPUT_WIN_NUMBER_DIVIDE_STRING).length != DEFAULT_SIZE;
	}
}
