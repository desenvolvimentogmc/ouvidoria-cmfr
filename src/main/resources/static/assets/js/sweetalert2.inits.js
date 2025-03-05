function showLoading(title, text) {
  Swal.fire({
    title,
    text,
    allowOutsideClick: false,
    didOpen: () => {
      Swal.showLoading();
    },
  });
}

function closeLoading() {
  Swal.close();
}

function showError(title, text) {
  Swal.fire({
    icon: "error",
    title,
    text,
    confirmButtonText: "OK",
  });
}

function showSuccess(title, text) {
  Swal.fire({
    icon: "success",
    title,
    text,
    confirmButtonText: "OK",
    toast: true
  });
}
