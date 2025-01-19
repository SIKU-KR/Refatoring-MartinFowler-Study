package siku.domain;

public class Play {
    private String name;
    private String type;

    // 기본 생성자
    public Play() {}

    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Play{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
