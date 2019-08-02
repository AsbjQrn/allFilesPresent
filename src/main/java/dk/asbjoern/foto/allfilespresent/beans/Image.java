package dk.asbjoern.foto.allfilespresent.beans;

public class Image implements Comparable<Image> {

    private String md5sum;
    private String absolutePath;

    public Image(String md5sum, String absolutePath) {
        this.md5sum = md5sum;
        this.absolutePath = absolutePath;
    }

    public String getMd5sum() {
        return md5sum;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    @Override
    public int compareTo(Image other) {
        return this.md5sum.compareTo(other.getMd5sum());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return md5sum.equals(image.md5sum);
    }

    @Override
    public int hashCode() {
        return md5sum.hashCode();
    }

    @Override
    public String toString() {
        return "Image{" +
                "md5sum='" + md5sum + '\'' +
                ", absolutePath='" + absolutePath + '\'' +
                '}';
    }
}
