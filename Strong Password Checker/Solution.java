class Solution {
    public int strongPasswordChecker(String pwd) { 
	int len = pwd.length();

        if (len <= 2)
            return 6 - len;

        boolean isHasUppercase = false, isHasLowercase = false, isHasDigtil = false;
        int repeatCount = 1, repeatThreeCharactersCount = 0, needDeleteCount = 0;
        int[] deleteGroup = new int[3];
        char prevCharacter = 0;

        int invaildCharCount = 0, lenMotion = 0;

        for (var i = 0; i < len; i++) {
            if (Character.isDigit(pwd.charAt(i))) {
                isHasDigtil = true;
            }

            if (Character.isLowerCase(pwd.charAt(i))) {
                isHasLowercase = true;
            }

            if (Character.isUpperCase(pwd.charAt(i))) {
                isHasUppercase = true;
            }


            if (prevCharacter == 0) {
                prevCharacter = pwd.charAt(i);
            } else {
                if (prevCharacter == pwd.charAt(i)) {
                    repeatCount++;
                } else {
                    repeatThreeCharactersCount += repeatCount / 3;
                    if (repeatCount >= 3) {
                        ++deleteGroup[repeatCount % 3];
                    }
                    repeatCount = 1;
                }
                prevCharacter = pwd.charAt(i);
            }
        }

        repeatThreeCharactersCount += repeatCount / 3;
        if (repeatCount >= 3) {
            ++deleteGroup[repeatCount % 3];
        }

        if (!isHasDigtil)
            invaildCharCount++;

        if (!isHasUppercase)
            invaildCharCount++;

        if (!isHasLowercase)
            invaildCharCount++;

        if (len < 6) {
            lenMotion = 6 - len;

            return Math.max(repeatThreeCharactersCount, Math.max(invaildCharCount, lenMotion));
        }

        if (len <= 20) {
            return Math.max(invaildCharCount, repeatThreeCharactersCount);
        }

        lenMotion = len - 20;

        if (lenMotion <= deleteGroup[0]) repeatThreeCharactersCount -= lenMotion;
        else if ((lenMotion - deleteGroup[0]) <= 2 * deleteGroup[1])
            repeatThreeCharactersCount -= deleteGroup[0] + (lenMotion - deleteGroup[0]) / 2;
        else
            repeatThreeCharactersCount -= deleteGroup[0] + deleteGroup[1] + (lenMotion - deleteGroup[0] - 2 * deleteGroup[1]) / 3;

        return lenMotion + Math.max(repeatThreeCharactersCount, invaildCharCount);

}
}