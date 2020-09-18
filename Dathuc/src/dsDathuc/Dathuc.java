package dsDathuc;

public class Dathuc {
   private Donthuc[] dsDonthuc;
   int length;

   public Dathuc() {
      length = 1;
      dsDonthuc = new Donthuc[1];
      dsDonthuc[0] = new Donthuc();
   }

   public Dathuc(Dathuc dathuc) {
      this.dsDonthuc = new Donthuc[dathuc.length];
      for (int i = 0; i < dathuc.length; i++) {
         this.dsDonthuc[i] = new Donthuc(dathuc.layDonthuc(i));
         length = dathuc.length;
      }
   }

   public Dathuc(Donthuc[] ds) {
      this.dsDonthuc = ds;
   }

   public Dathuc(String string) {
      Donthuc donthuc;
      string = string.toLowerCase();
      string = string.replace("x^0", "");
      string = string.replace("--", "+");
      string = string.replace("++", "+");
      string = string.replace("+-", "-");
      string = string.replace("+", ",");
      string = string.replace("-", ",-");
      string = string.replace("x^", "x");
      String[] arrStr = string.split(",");
      for (int i = 0; i < arrStr.length; i++) {
         donthuc = new Donthuc(arrStr[i]);
         this.themDonthuc(donthuc);
      }

   }

   public void themDonthuc(Donthuc donthuc) {

      Donthuc[] dsDonthuc = new Donthuc[this.length + 1];
      for (int i = 0; i < this.length; i++)
         dsDonthuc[i] = this.dsDonthuc[i];
      dsDonthuc[this.length] = new Donthuc(donthuc.laySo(), donthuc.layMu());
      this.dsDonthuc = new Donthuc[this.length + 1];
      this.dsDonthuc = dsDonthuc;
      this.length++;
   }

   // lấy ra toàn bộ đơn thức
   public Donthuc[] laydsDonthuc() {
      return dsDonthuc;
   }

   // lấy ra 1 đơn thức
   public Donthuc layDonthuc(int i) {
      return dsDonthuc[i];
   };

   // sửa 1 đơn thức tại vị trí i
   public void suaDonthuc(int i/* vị trí */, float so, int mu) {
      this.dsDonthuc[i] = new Donthuc(so, mu);
   }

   public void suaDonthuc(int i/* vị trí */, Donthuc donthuc) {
      this.dsDonthuc[i] = donthuc;
   }

   // trả về độ dài của đa thức
   public int layDodai() {
      return this.length;
   }

   // trả về 1 da thức đã sắp xếp theo số mũ tăng dần
   public Dathuc sapxepDathuc() {
      Dathuc dathuc = new Dathuc(this);
      for (int i = 0; i < this.length - 1; i++)
         for (int j = i; j < this.length; j++)
            if (dathuc.layDonthuc(j).layMu() < dathuc.layDonthuc(i).layMu())
               dathuc.doichoDonthuc(j, i);
      return dathuc;
   }

   public void doichoDonthuc(int i, int j/* vị trí 2 đơn thức cần đổi chỗ */) {
      Donthuc donthuc = new Donthuc();
      donthuc = this.dsDonthuc[i];
      this.dsDonthuc[i] = this.dsDonthuc[j];
      this.dsDonthuc[j] = donthuc;
   }

   // Trả về 1 đa thức đã thu gọn
   public Dathuc thugonDathuc() {

      Dathuc dathuc1 = new Dathuc(this);
      // sắp xếp lại
      System.out.println(dathuc1.toString());
      dathuc1 = dathuc1.sapxepDathuc();
      System.out.println(dathuc1.toString());
      // xử lí thu gọn
      Dathuc dathuc = new Dathuc();
      int mu, mu1, mu2;
      float so;
      Donthuc donthuc;
      int i = 0;
      // vì ô đầu tiên là (0.0) nên sửa ô đầu trong kết quả thành ô đầu của đa thức đã
      // thu gọn

      System.out.println(dathuc1.layDonthuc(0).layMu());
      System.out.println(dathuc1.layDonthuc(0).laySo());
      mu = dathuc1.layDonthuc(0).layMu();
      so = dathuc1.layDonthuc(0).laySo();
      donthuc = new Donthuc(so, mu);
      dathuc.suaDonthuc(0, donthuc);
      while (i < dathuc1.length - 1) {
         mu1 = dathuc1.layDonthuc(i).layMu();
         mu2 = dathuc1.layDonthuc(i + 1).layMu();
         if (mu1 == mu2) {
            so = donthuc.laySo() + dathuc1.layDonthuc(i + 1).laySo();
            donthuc = new Donthuc(so, mu1);
            dathuc.suaDonthuc(dathuc.layDodai() - 1, donthuc);
         } else {
            so = dathuc1.layDonthuc(i + 1).laySo();
            donthuc = new Donthuc(so, mu2);
            dathuc.themDonthuc(donthuc);
         }
         if (i == dathuc1.length - 1) {
            dathuc.themDonthuc(donthuc);
         }
         i++;
      }
      return dathuc;
   }

   public Dathuc xoaDonthuc(int i) {
      Dathuc ketqua = new Dathuc();
      ketqua.suaDonthuc(0, this.dsDonthuc[1]);
      for (int j = 2; j < this.length; j++) {
         ketqua.themDonthuc(this.layDonthuc(j));
      }

      return ketqua;
   }

   public int[] minus(int[] dsDonthuc1, int[] dsDonthuc2) {
      int i;
      int length = Math.max(dsDonthuc1.length, dsDonthuc2.length);
      int[] result = new int[length];
      initArr(result);
      for (i = 0; i < length; i++) {
         result[i] = dsDonthuc1[i] - dsDonthuc2[i];
      }
      return result;
   }

   public int[] muptiply(int[] dsDonthuc1, int[] dsDonthuc2) {
      int i, j;
      int length = dsDonthuc1.length + dsDonthuc2.length - 1;
      int[] result = new int[length];
      initArr(result);
      for (i = 0; i < dsDonthuc1.length; i++) {
         for (j = 0; j < dsDonthuc2.length; j++) {
            result[i + j] = result[i + j] + dsDonthuc1[i] * dsDonthuc2[i];
         }
      }
      return result;
   }

   public int[] initArr(int[] dsDonthuc) {
      for (int i = 0; i < dsDonthuc.length; i++)
         dsDonthuc[i] = 0;
      return dsDonthuc;
   }

   // Cộng với đa thức(dathuc ) trả về 1 đa thức đã cộng
   public Dathuc CongDathuc(Dathuc dathuc2) {
      Dathuc dathuc1 = this.thugonDathuc();
      Dathuc ketqua = new Dathuc();
      dathuc2 = dathuc2.thugonDathuc();
      int i = 0;
      int j = 0;

      i++;
      while (true) {
         if (dathuc1.layDonthuc(i).layMu() < dathuc2.layDonthuc(j).layMu()) {
            ketqua.themDonthuc(dathuc1.layDonthuc(i));
            i++;
         }
         if (dathuc1.layDonthuc(i).layMu() > dathuc2.layDonthuc(j).layMu()) {
            ketqua.themDonthuc(dathuc1.layDonthuc(j));
            j++;
         }
         if (dathuc1.layDonthuc(i).layMu() == dathuc2.layDonthuc(i).layMu()) {
            j++;
            i++;
         }
         break;
      }
      return ketqua;
   }

   // chuyển đa thức thành chuỗi
   public String toString() {
      String ketqua = new String();
      for (int i = 0; i < this.length; i++) {
         ketqua += this.dsDonthuc[i] + " + ";
      }
      ketqua = ketqua.substring(0, ketqua.length() - 2);
      return ketqua;
   }

   public static void main(String[] args) throws Exception {

      Dathuc dathuc = new Dathuc("4x+0x^0+42x^4");

      dathuc = dathuc.thugonDathuc();
      // dathuc = dathuc.Derivative();
      String asd = dathuc.toString();

      System.out.println(asd);
   }
}
