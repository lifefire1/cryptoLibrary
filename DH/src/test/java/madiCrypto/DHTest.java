package madiCrypto;

import madiCrypto.DH;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.Random;

public class DHTest {

    private BigInteger[] testPrimaryValue = {new BigInteger("900900900900990990990991"),
            new BigInteger("8683317618811886495518194401279999999"),
            new BigInteger("263130836933693530167218012159999999"),
            new BigInteger("11111111111111111111111"),
            new BigInteger("123426017006182806728593424683999798008235734137469123231828679"),
            new BigInteger("170141183460469231731687303715884105727"),
            new BigInteger("162259276829213363391578010288127"),
            new BigInteger("1298074214633706835075030044377087")};

    private long[] testPrimaryBase = {2003857, 2003861, 2003863, 2003879, 2003917, 2003927, 2003951,
            2003959, 2003971, 2003999, 2004001, 2004007, 2004017, 2004029, 2004043, 2004049, 2004073,
            2004083, 2004091, 2004097, 2004109, 2004131, 2004133, 2004137, 2004209, 2004227, 2004251,
            2004269, 2004271, 2004293, 2004313, 2004341, 2004347, 2004349, 2004377, 2004383, 2004421,
            2004433, 2004461, 2004463, 2004479, 2004511, 2004529, 2004539, 2004559, 2004571, 2004577,
            2004593, 2004601, 2004631, 2004641, 2004647, 2004661, 2004679, 2004701, 2004713};

    @Test
    public void testCalculateSecretKey() {
        // Границы массивов
        final int TEST_VALUES_LENGTH = testPrimaryValue.length;
        final int TEST_BASES_LENGTH = testPrimaryBase.length;

        // Создание одного экземпляра Random для всех генераций
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            // Генерация случайного индекса и базы
            int index = random.nextInt(TEST_VALUES_LENGTH);
            long base = testPrimaryBase[random.nextInt(TEST_BASES_LENGTH)];

            // Создание экземпляров DH
            DH alice = new DH(testPrimaryValue[index], new BigInteger(Long.toString(base)));
            DH bob = new DH(testPrimaryValue[index], new BigInteger(Long.toString(base)));

            // Вычисление секретных ключей
            alice.calculateSecretKey(bob.getPublicKey());
            bob.calculateSecretKey(alice.getPublicKey());

            // Проверка наличия секретных ключей и их равенство
            assertTrue(alice.getSecretKey().compareTo(BigInteger.ZERO) != 0, "Secret key of Alice is zero");
            assertTrue(bob.getSecretKey().compareTo(BigInteger.ZERO) != 0, "Secret key of Bob is zero");
            assertEquals(alice.getSecretKey(), bob.getSecretKey(), "Secret keys are not equal");
        }
    }
}
