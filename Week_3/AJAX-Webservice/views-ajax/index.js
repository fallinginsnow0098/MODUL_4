let index = 0;
let url = "http://localhost:8080/api/products";
function displayProduct(product){
    return `<tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.description}</td>
                <td><button class="btn btn-danger" onclick="deleteProduct(${product.id})" style="text-decoration: none">DELETE</button></td>
                <td><button class="btn btn-warning" onclick="showFormEdit(${product.id})" style="text-decoration: none">EDIT</button></td>
            </tr>`
}
function showFormCreate() {
    document.getElementById("form").reset()
    document.getElementById("form").hidden = false;
    document.getElementById("form-button").onclick = function () {
        addNewProduct();
    }
}
function getProduct() {
    $.ajax({
        type: "GET",
        url: `${url}`,
        success: function (data){
            let content = `<tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Description</th>
                                <th colspan="2">Action</th>
                           </tr>`
            for (let i = 0; i < data.length; i++) {
                content += displayProduct(data[i]);
            }
            document.getElementById("list-product").innerHTML = content;
            document.getElementById("form").hidden = true;
        }
    });
}

function addNewProduct() {
    let name = $('#name').val();
    let price = $('#price').val();
    let quantity = $('#quantity').val();
    let description = $('#description').val();
    let newProduct = {
        name: name,
        price: price,
        quantity: quantity,
        description: description
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newProduct),
        url: "defaultURL",
        success: function () {
            getProduct();
        }
    });
    event.preventDefault();
}
function showFormEdit(id) {
    $.ajax({
        type: "GET",
        url: `${url}/${id}`,
        success: function (data) {
            $('#name').val(data.name);
            $('#price').val(data.price);
            $('#quantity').val(data.quantity);
            $('#description').val(data.description);
            index = data.id;
            document.getElementById("form").hidden = false;
            document.getElementById("form-button").onclick = function () {
                editProduct()
            };
        }
    });
}

function editProduct() {
    //lay du lieu
    let name = $('#name').val();
    let price = $('#price').val();
    let quantity = $('#quantity').val();
    let description = $('#description').val();
    let newProduct = {
        name: name,
        price: price,
        quantity: quantity,
        description: description
    };
    // goi ajax
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(newProduct),
        //tên API
        url: `http://localhost:8080/api/products/${index}`,
        //xử lý khi thành công
        success: function () {
            getProduct()
        }
    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}

function deleteProduct(id) {
    $.ajax({
        type: "DELETE",
        url: `${url}/${id}`,
        success: function () {
            getProduct()
        }
    });
}

function searchProduct() {
    let search = document.getElementById("search").value;
    $.ajax({
        type: "GET",
        //tên API
        url: `${url}search?search=${search}`,
        //xử lý khi thành công
        success: function (data) {
            // hien thi danh sach o day
            let content = '<tr>\n' +
                '<th>ProductName</th>\n' +
                '<th>Price</th>\n' +
                '<th>Quantity</th>\n' +
                '<th>Desciption</th>\n' +
                '<th colspan="2">Action</th>\n' +
                '</tr>';
            for (let i = 0; i < data.length; i++) {
                content += displayProduct(data[i]);
            }
            document.getElementById('productList').innerHTML = content;
            document.getElementById("searchForm").reset()
        }
    });
    event.preventDefault();
}