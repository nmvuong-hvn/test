import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class CommonPickerTimeAndEdt extends StatefulWidget {
  Widget icon;
  final VoidCallback? onClick;
  String dateTime ;
  Color ? colorBorder ;

  CommonPickerTimeAndEdt({super.key, required this.icon, this.onClick, this.colorBorder, required this.dateTime});

  @override
  State<CommonPickerTimeAndEdt> createState() => _CommonPickerTimeAndEdtState();
}

class _CommonPickerTimeAndEdtState extends State<CommonPickerTimeAndEdt> {
  String date = DateFormat('dd/MM/yyyy').format(DateTime.now());
  late Widget iconSuffix;
  VoidCallback? onButtonClick;
  Color ? color;
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    iconSuffix = widget.icon;
    onButtonClick = widget.onClick;
    color = widget.colorBorder;
  }

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: double.infinity,
      child: TextField(
        decoration: InputDecoration(
            contentPadding: const EdgeInsets.symmetric(
                vertical: 13, horizontal: 13),
            hintText: widget.dateTime.isEmpty ? date : widget.dateTime,
            hintStyle: const TextStyle(
                color: Colors.black,
                fontWeight: FontWeight.normal
            ),
            suffixIcon:
            IconButton(onPressed: onButtonClick, icon: iconSuffix),
            border: OutlineInputBorder(
                borderSide: BorderSide(
                  color: (color != null) ? color! : const Color(0xFF000000),
                )
            )
        ),
        keyboardType: TextInputType.datetime,
      ),
    );
  }
}