$(function () {
  redrawOutlineButtons();
  $('.form-group .btn-group-toggle label.btn').click(redrawOutlineButtons);
});

var buttonSuffixArray = ['primary', 'secondary', 'success', 'danger', 'warning', 'info', 'light', 'dark',
  'link'
];

function redrawOutlineButtons() {
  $('.form-group .btn-group-toggle label.btn').each(function () {
    var buttonClassName = getButtonClass($(this));
    var tmp = buttonClassName.split('-');
    var buttonSuffixName = tmp[tmp.length - 1];
    var isChecked = $(this).children('input[type=radio]:checked').length > 0;

    $(this).removeClass(buttonClassName).addClass((isChecked ? 'btn-' : 'btn-outline-') +
      buttonSuffixName);
  })
}

function getButtonClass($button) {
  for (let i = 0; i < buttonSuffixArray.length; i++) {
    const el = buttonSuffixArray[i];
    
    if ($button.hasClass('btn-' + el)) {
      return 'btn-' + el;
    } else if ($button.hasClass('btn-outline-' + el)) {
      return 'btn-outline-' + el
    }
  }

  return '';
}