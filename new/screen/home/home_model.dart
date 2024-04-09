class HomeModel {
  late String idHome;

  late String nameHome;

  late String addressHome;

  HomeModel.name(this.idHome, this.nameHome, this.addressHome);

  HomeModel(this.idHome, this.nameHome, this.addressHome);

  Map<String, String> toHomeMap() {
    return {'idHome': idHome, 'nameHome': nameHome, 'addressHome': addressHome};
  }

  HomeModel.fromJson(Map<String, dynamic> dataMap) {
    idHome = dataMap['idHome'];
    nameHome = dataMap['nameHome'];
    addressHome = dataMap['addressHome'];
  }

  @override
  String toString() {
    return 'HomeModel{idHome: $idHome, nameHome: $nameHome, addressHome: $addressHome}';
  }
}
