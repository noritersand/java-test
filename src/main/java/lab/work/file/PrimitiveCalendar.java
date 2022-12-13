package lab.work.file;

import java.util.Scanner;

/**
 * 이거 학원 다닐 때 만든거였던가...
 *
 * @author noritersand
 * @since 2020-01-29
 */
public class PrimitiveCalendar {

    public static void main(String[] args) {
        printWithoutAPI();
    }

    static void printWithoutAPI() {
        // 요구사항] 년과 월을 입력받아서 달력을 출력하시오
        // 조건] 1. 해당 년,월의 1일이 무슨 요일?
        // 1.1 서기 1년 1월 1일부터 년,월의 1일까지의 총일수 % 7 = ?(요일)
        // // sum % 7 = 1(월) | sum % 7 = 2(화)
        // 2. 해당 년,월의 마지막일?
        // 2.1 윤년
        Scanner scan = new Scanner(System.in);

        System.out.print("년도? : ");
        int inputYear = scan.nextInt(); // 년도 입력
        System.out.printf("월? %2s: ", "");
        int inputMonth = scan.nextInt(); // 월 입력

        int totalDays = mDaysY(inputYear) + mDaysM(inputYear, inputMonth); // 총경과일
        int lastDay = mLastDay(inputYear, inputMonth); // 마지막날

        System.out.printf("%1s일%1s월%1s화%1s수%1s목%1s금%1s토\n", "", "", "", "", "", "", "");
        for (int j = 1; j <= totalDays % 7; ++j) {
            System.out.printf("%3s", "");
        }

        for (int i = 1; i <= lastDay; ++i) {
            if ((totalDays - 1 + i) % 7 != 6)
                System.out.printf("%3d", i);
            else
                System.out.printf("%3d\n", i);
        }
        System.out.println();

        scan.close();
    }

    /**
     * 입력년, 월의 마지막 날
     *
     * @param year
     * @param month
     * @return
     * @author fixalot
     */
    public static int mLastDay(int year, int month) {
        int result = 0;
        switch (month) {
            case 2:
                if (isLeapYear(year)) {
                    // 윤년 29일
                    result += 29;
                    break;
                } else {
                    // 평년 28일
                    result += 28;
                    break;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                result += 30;
                break;
            default: // 1월, 3월, 5월, 7월, 8월, 10월, 12월
                result += 31;
        }
        return result;
    }

    /**
     * 1월부터 ((입력월-1월)+1일)까지 총 경과일
     *
     * @param year
     * @param month
     * @return
     * @author fixalot
     */
    public static int mDaysM(int year, int month) {
        int sum = 0; // 카운트
        // 입력월까지 루프
        for (int i = 1; i < month; ++i) {
            switch (i) {
                case 2:
                    if (isLeapYear(year)) {
                        // 윤년이면 29일
                        sum += 29;
                        break;
                    } else {
                        // 평년은 28일
                        sum += 28;
                        break;
                    }
                case 4:
                case 6:
                case 9:
                case 11:
                    sum += 30;
                    break;
                default: // 1월, 3월, 5월, 7월, 8월, 10월, 12월
                    sum += 31;
            }
        }
        return sum + 1;
    }

    /**
     * AD010101부터 (입력년도-1년)까지 총 경과일
     *
     * @param year
     * @return
     * @author fixalot
     */
    public static int mDaysY(int year) {
        int sum = 0; // 카운트
        // 총 경과일 수 중 지난년도까지 계산
        for (int i = 1; i < year; ++i) {
            sum += 365; // 여기까지가 365*(입력년도-1)
            if (isLeapYear(i)) {
                sum += 1; // 윤년일때 += 1
            }
        }
        return sum;
    }

    /**
     * 윤년 구하기
     *
     * @param year
     * @return 윤년이면 true. false는 평년.
     * @author fixalot
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }

}
