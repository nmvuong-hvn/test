import 'package:flutter/material.dart';

class CommonDropMenuItem extends StatelessWidget {
  List<String> items = List.empty();
  int initialValue = 0;
  int offsetWidth = 75;
  final void Function(String)? onSelected;

  CommonDropMenuItem(
      {super.key,
        required this.items,
        required this.initialValue,
        required this.offsetWidth,
        this.onSelected});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      child: DropdownMenu<String>(
        width: MediaQuery.of(context).size.width - offsetWidth,
        initialSelection: items[initialValue],
        onSelected: (String? value) {
          if(value != null && onSelected != null) {
            onSelected!.call(value);
          }
        },
        dropdownMenuEntries:
        items.map<DropdownMenuEntry<String>>((String value) {
          return DropdownMenuEntry<String>(value: value, label: value);
        }).toList(),
      ),
    );
  }
}