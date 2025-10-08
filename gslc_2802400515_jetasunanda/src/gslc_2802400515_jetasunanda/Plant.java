/*
 * Ini adalah class untuk menyimpan data plant
 */

package gslc_2802400515_jetasunanda;

public class Plant {
	// deklarasi variable yang ingin disimpan
	String plantType;
	String sciName;
	String name;
	Integer growthTime;

	// fungsi yang dipanggil ketika menu 4 (water plants) dijalankan
	public void reduceGrowthTime() {
		if (this.growthTime > 0) {
			this.growthTime -= 1;
		}
	}
}
