package darkchoco.msacomponents.util;

import java.security.SecureRandom;

public class IdGenerator {

    private static final int BOUND = 10_000;
    private static final SecureRandom RANDOM = new SecureRandom();  // thread-safe

    // primitive 타입이 좋지만 아래 메소드를 사용하는 대부분 Class가 Long 타입을 필요로 해서 Object 타입으로 return 한다.
    public static Long create() {
        long time = System.currentTimeMillis();
        int random = RANDOM.nextInt(BOUND);
        return time*BOUND + random;
    }
}
