package dsDathuc;

public class Donthuc {
   int mu;
   float so;

   public Donthuc() {
      this.so = 0;
      this.mu = 0;
   }

   public Donthuc(Donthuc donthuc) {
      this.mu = donthuc.layMu();
      this.so = donthuc.laySo();
   }

   public Donthuc(float so, int mu) {
      System.out.println(mu);
      if (so == 0) {
         this.so = 0;
         this.mu = 0;
      } else {
         this.so = so;
         this.mu = mu;
      }

   }

   public Donthuc(String donthuc) {
      String[] pt = donthuc.split("x");
      if (donthuc.contains("x")) {
         if (donthuc.charAt(0) != 'x' && donthuc.charAt(donthuc.length() - 1) != 'x') {
            so = Float.parseFloat(pt[0]);
            mu = Integer.parseInt(pt[1]);

         }
         if (donthuc.charAt(0) == 'x' && donthuc.charAt(donthuc.length() - 1) == 'x') {
            so = 1;
            mu = 1;
         }

         if (donthuc.charAt(0) == 'x' && donthuc.charAt(donthuc.length() - 1) != 'x') {
            so = 1;
            mu = Integer.parseInt(pt[1]);
         }

         if (donthuc.charAt(0) != 'x' && donthuc.charAt(donthuc.length() - 1) == 'x') {
            so = Float.parseFloat(pt[0]);
            mu = 1;
         }

      } else {
         so = Float.parseFloat(pt[0]);
         mu = 0;
      }
   }

   // cộng với đơn thức(donthuc) cùng số mũ -trả về 1 đơn thức

   public Donthuc congDonthuc(Donthuc donthuc) {
      if (this.mu == donthuc.layMu()) {
         int mu = this.mu;
         float so = this.so + donthuc.laySo();
         Donthuc ketqua = new Donthuc(so, mu);
         return ketqua;
      } else
         throw new Error("chỉ cộng được với đơn thức cùng số mũ");
   }

   // trừ cho đơn thuc(donthuc) cùng số mũ - trả về 1 đơn thức
   public Donthuc truDonthuc(Donthuc donthuc) {
      if (this.mu == donthuc.layMu()) {
         int mu = this.mu;
         float so = this.so - donthuc.laySo();
         Donthuc ketqua = new Donthuc(so, mu);
         return ketqua;
      } else {
         throw new Error("chỉ trừ được cho đơn thức cùng số mũ");
      }
   }

   public Donthuc daohamDonthuc() {
      float so = this.so;
      int mu = this.mu;
      if (this.mu == 0) {
         so = 0;
         mu = 0;
      } else {
         mu = this.mu - 1;
         so = so * this.mu;
      }
      Donthuc ketqua = new Donthuc(so, mu);
      return ketqua;
   }

   public Donthuc nhanDonthuc(Donthuc donthuc) {
      int mu = this.mu;
      float so = this.so;
      mu = mu * donthuc.layMu();
      so = so * donthuc.laySo();
      Donthuc ketqua = new Donthuc(so, mu);
      return ketqua;
   }

   public Donthuc chiaDonthuc(Donthuc donthuc) {
      int mu = this.mu;
      float so = this.so;
      mu = mu / donthuc.layMu();
      so = so / donthuc.laySo();
      Donthuc ketqua = new Donthuc(so, mu);

      return ketqua;
   }

   public int layMu() {
      return this.mu;
   }

   public float laySo() {
      return this.so;
   }

   // cộng thêm số cho đơn thức
   public Donthuc congDonthuc(float so) {
      Donthuc donthuc = new Donthuc(this.so + so, this.mu);
      return donthuc;
   }

   public String toString() {
      String str = new String();
      str = Float.toString(this.so) + "x^" + Integer.toString(mu);
      if (mu == 0) {
         str = Float.toString(this.so);
      }
      if (mu == 1)
         str = Float.toString(this.so) + "x";
      return str;
   }

   public void printf() {

      System.out.print(this.so + "x^" + this.mu);
   }

   public static void main(String[] args) {
      Donthuc dt = new Donthuc(0, -1);
      System.out.println(dt.toString());
   }
}