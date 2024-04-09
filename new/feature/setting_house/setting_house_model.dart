

class SettingHouseModel {
  late final int dayOfMonth ;
  late final int priceElectricity ;
  late final int priceWater ;
  late final int priceRoom ;
  late final int priceInternet ;
  late final int priceSecurity ;

  SettingHouseModel.name(this.dayOfMonth, this.priceElectricity,
      this.priceWater, this.priceRoom, this.priceInternet, this.priceSecurity);

  SettingHouseModel(this.dayOfMonth, this.priceElectricity, this.priceWater,
      this.priceRoom, this.priceInternet, this.priceSecurity);


}