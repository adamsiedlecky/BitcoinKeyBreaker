package breaker.beans;

import org.bitcoinj.core.Base58;

import java.math.BigInteger;

public class KeyGenerator {


    private final BigInteger maxValue = BigInteger.valueOf(2).pow(256);
    private BigInteger numberSeed;
    private String stringSeed;
    private SHAUtility shaUtility = new SHAUtility();

    public String convertToWIF(String seed) {
        //System.out.println("Seed: "+seed);
        while (seed.length() < 64) {
            seed = "0" + seed;
        }
        String temporary = "80" + seed; //
        //System.out.println("2. "+temporary);
        String hashed = shaUtility.getSHA(temporary);
        //System.out.println("3. "+hashed);
        String hashedTwice = shaUtility.getSHA(hashed);
        //System.out.println("4. "+hashedTwice);
        String checksum = "" + hashedTwice.toCharArray()[0] + hashedTwice.toCharArray()[1] + hashedTwice.toCharArray()[2] + hashedTwice.toCharArray()[3] + hashedTwice.toCharArray()[4] + hashedTwice.toCharArray()[5] + hashedTwice.toCharArray()[6] + hashedTwice.toCharArray()[7];
        //System.out.println("5. Checksum: "+checksum);
        String temoporary = temporary + checksum;
        String result = Base58.encode(SHAUtility.hexStringToByteArray(temoporary));

        return result;
    }


}
