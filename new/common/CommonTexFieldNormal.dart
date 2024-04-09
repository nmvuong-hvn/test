import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class CommonTextFieldNormal extends StatefulWidget {
  String title ;
  String hintText ;
  TextEditingController? controller ;
  CommonTextFieldNormal({super.key, required this.title, required this.hintText,required this.keyboardType,required this.controller});
  TextInputType keyboardType;

  @override
  State<CommonTextFieldNormal> createState() => _CommonTextFieldNormalState();
}

class _CommonTextFieldNormalState extends State<CommonTextFieldNormal> {
  final currency = NumberFormat("#,###", "en_US");

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(widget.title,
            style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 15)),
        const SizedBox(height: 3),
        TextField(
          controller: widget.controller,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            contentPadding:
                const EdgeInsets.symmetric(vertical: 13, horizontal: 13),
            hintText: widget.hintText,
          ),
          keyboardType: widget.keyboardType,
          onChanged: (data) {
            widget.controller?.value = TextEditingValue(
              text: currency.format(widget.controller?.text),
            );
          },

        )
      ],
    );
  }
}
