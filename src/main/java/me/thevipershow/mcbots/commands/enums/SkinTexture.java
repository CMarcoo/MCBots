/*
 * 02/04/20, 16:21 - MCBots
 * ------------------------------------------------------------------------------------
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package me.thevipershow.mcbots.commands.enums;

public enum SkinTexture {

    THEVIPERSHOW("eyJ0aW1lc3RhbXAiOjE1ODU4MzcyNTI0NDksInByb2ZpbGVJZCI6IjE5OTM1MDAxYzIyZTQ4NTA4ZDhiZjE2OTIzMTQ1YzQ2IiwicHJvZmlsZU5hbWUiOiJUaGVWaXBlclNob3ciLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIzYzZhYmI3YWZlMDM2MjNmYWY5MzA0YWU0MGMxM2FiYWNlYzgyNWY1OWMwZjgzM2ZjNTdlNWQ2ZGUxOGViYSJ9fX0=",
                         "S9GzewXANCo19RlaTVKldw4pmzjIQnrm+4TpsCzyFcsvrzOm1o6427DfNJcn6Zl9rFzDcIVD4f/4XjWplY+bw9IJgXgl287Uu+kPkxdAiL5NK/FMiEE3txt8HjbnLOVw4Kp/OtJFiNQp21pOts7xqIqM5t2tyNHox0ZGcmpzskWIY83XU7ipZaeTOkrW4i/an7XhgbhzmvdpHv9Zckn1HgxN5hbE2iivuxMzb0XgltaMyaF5LPrKBj3ieYxhqQPeTVI2cGOrNDyambRiJanz+7WfzrFoYGju+XH1+91yq/anMQqgNS1zS/B1P602GJ8XHY8TWLQZ0asHFLwq5Hx9MsnQJO9zWQjM0awyAjmy0xM62pKQ3vk4v8HSmcuVZJImMTHZrcpl2kn7je5MXmpuPTHFfAPpSW3ctityYXadmGvUvaFU1gFIrfzRhF93JDlpBA4A0Y+/rcVyyEiVPWlKFJ0PlBt52asRTc/3uS7rsOgtkhpmheemXXyek+roWbHDxBBcBk2tb/Qc+Ql52A506kX490BnwPOL2xCqo9i9+HK4DB8PhlBX9PN7pYsG+wwHWN0SfzzQsJRYIY3l0PLyXBqF2SOqeW1tp2B14gt7cFzktpTKHT17jB+B180cT4r+5V0t8669tylckaaTtVPhMwuyOJlsnHt0P2cHDy+PUAY=");

    private final String value,signature;

    SkinTexture(String value, String signature) {
        this.value = value;
        this.signature = signature;
    }

    public String getValue() {
        return value;
    }

    public String getSignature() {
        return signature;
    }
}
