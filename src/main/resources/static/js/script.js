const app = angular.module("my-app", [])

app.controller('shopping-controller', function($scope, $http) {
    $scope.cart = {
        products: [],
        addToCart(id) {
            const check_product = this.products.find(item => item.id == id);
            if(check_product){
                check_product.count++;
                this.saveToLocalStorage();
            } else{
                $http.get(`http://localhost:8080/products/${id}`).then(res =>{
                    res.data.count = 1;
                    this.products.push(res.data);
                    this.saveToLocalStorage();
                })
            }
        },

        removeFromCart(id) {
            var indexOfItem = this.products.findIndex(item => item.id == id);
            this.products.splice(indexOfItem, 1);
            this.saveToLocalStorage();

        },

        clearCart() {
            this.products = [];
            this.saveToLocalStorage();
        },

        totalOfProduct(item){},

        countProductsOfCart(){
            let amountProducts = this.products.length;
            return amountProducts
        },

        totalAllProducts() {
            return this.products.map(item => item.price * item.count).reduce(total, item => total += item, 0);
        },

        saveToLocalStorage() {
            var listSave = JSON.stringify(angular.copy(this.products));
            localStorage.setItem("products", listSave)
        },

        loadFromLacalStorage() {
            var loadList = localStorage.getItem("products");
            this.products = loadList ? JSON.parse(loadList) : []
        }

    }

    function formatDate(date) {
        // Lấy các phần của ngày, tháng và năm từ đối tượng Date
        let day = date.getDate();
        let month = date.getMonth() + 1; // Lưu ý rằng tháng trong JavaScript bắt đầu từ 0 (0 = January, 11 = December)
        let year = date.getFullYear();

        // Thêm số 0 vào trước các giá trị ngày và tháng nếu chúng nhỏ hơn 10 (để đảm bảo định dạng "dd/mm/yyyy")
        if (day < 10) {
            day = '0' + day;
        }
        if (month < 10) {
            month = '0' + month;
        }

        // Trả về chuỗi định dạng "yyyy/mm/dd"
        return `${year}-${month}-${day}`;
    }

    $scope.order = {
        createDate: formatDate(new Date()),
        address: "",
        account: $("#name").val(),

        orderDetails () {
            return $scope.cart.products.map(item => {
                return {
                    productId: item.id,
                    name: item.name,
                    price: item.price,
                    amount: item.count
                }
            })
        },
        purchase() {
            var order = {
                username: this.account,
                orderDate: this.createDate,
                address: this.address,
                detail: this.orderDetails()
            }
            console.log("post this: " , order)

            $http.post("http://localhost:8080/orders", order).then(res => {
                console.log("res order: " , res.data)
                alert("Order success")
                $scope.cart.clearCart();
                location.href="/order/detail/" + res.data.id;
            }).catch(err => {
                alert("Order error")
                console.log(err)
            })
        }
    }

    $scope.cart.loadFromLacalStorage();
    console.log("date: " + $scope.order.createDate + " type: " + typeof($scope.order.createDate))

})