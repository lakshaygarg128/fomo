const foodItems = {
  cold: [
    {
      name: "Chole Bhature",
      image: "https://i.cdn.newsbytesapp.com/images/l198_38181592397420.jpg",
      description:
        "Chole Bhature also known as Chana Bhatura is one of the most popular Punjabi dish liked almost all over India.Chana Bhatura is a spicy, tasty and a filling dish",
      recipe: "https://youtu.be/FU1ZVLMbWjA",
    },
    {
      name: "Amritsari kulcha",
      image: "https://i.cdn.newsbytesapp.com/images/l198_26511593218357.jpg",
      description:
        "Amritsari Kulcha is crisp as well as soft leavened bread which is stuffed with boiled and mashed potatoes and spices. This bread is similar to Naan.",
      recipe: "https://youtu.be/pbI8PkiSB8E",
    },
    {
      name: "Gulab Jamun",
      image:
        "https://www.spiceupthecurry.com/wp-content/uploads/2020/08/gulab-jamun-recipe-2.jpg",
      description:
        "Soft, spongy and melt in mouth gulab jamuns drenched in delicately flavored sugar syrup is a traditional sweet in India.",
      recipe: "https://youtu.be/pyh0KTGGpGE",
    },
    {
      name: "Gajar ka Halwa",
      image:
        "https://www.vegrecipesofindia.com/wp-content/uploads/2021/11/gajar-halwa-carrot-halwa.jpg",
      description:
        "Classic Indian dessert Gajar Halwa is made with carrots, milk, sugar and ghee!.It’s one of quintessential winter dessert especially in North India and is rich and decadent.",
      recipe: "https://youtu.be/U2gGNzfBHBM",
    },
    {
      name: "Makki ki Roti & Sarso ka saag",
      image:
        "https://res.cloudinary.com/swiggy/image/upload/f_auto,q_auto,fl_lossy/bvitsplhietanq73wulw",
      description:
        "The classic Punjabi dish: Sarson da saag te makki di roti. Best had with some white butter, jaggery or honey.This winter special combination of meal makes everyone drool in winters. ",
      recipe: "https://youtu.be/tDE2_XKqAlQ",
    },
    {
      name: "Kesar ka Doodh",
      image:
        "https://www.whiskaffair.com/wp-content/uploads/2018/05/Saffron-Milk-3.jpg",
      description:
        "Brimming with calcium from milk and nuts, this healthy drink is so flavourful with strong hints of saffron that it is sure to be loved by kids, adults and elderly people as well",
      recipe: "https://youtu.be/rhD8ArXNopQ",
    },
  ],
  sunny: [
    {
      name: "Ice Cream",
      image:
        "https://www.thespruceeats.com/thmb/BYOHKcXhja-ez7Fr9obgBrDHJ1Y=/3064x2042/filters:fill(auto,1)/easy-chocolate-ice-cream-recipe-1945798-hero-01-45d9f26a0aaf4c1dba38d7e0a2ab51e2.jpg",
      description:
        "Who doesn’t love ice cream, after all, it’s one of the world’s favorite desserts?",
      recipe: "https://youtu.be/wt7q0Sk1e14",
    },
    {
      name: "Lassi",
      image:
        "https://www.vegrecipesofindia.com/wp-content/uploads/2021/04/lassi-recipe-1.jpg",
      description:
        "Lassi is a cooling, refreshing, probiotic drink perfect for the warm summer months. Popular in Punjab and North India, there are many variations of a lassi recipe, including mango lassi, dry fruits lassi, and malai lassi",
      recipe: "https://youtu.be/vKn1b9G1BjE",
    },
    {
      name: "Pani Puri / Gol Gappa",
      image:
        "https://myvegetarianroots.com/wp-content/uploads/2020/03/DSC_0218.jpeg",
      description:
        "Pani puri is a favorite chaat snack of many folks and ours too. With so many bursts of tastes and flavors in your mouth when you have pani puri, you just cannot have one 🙂",
      recipe: "https://youtu.be/OtQftx4TUh0",
    },
    {
      name: "Cocolate Shake",
      image:
        "https://tastesbetterfromscratch.com/wp-content/uploads/2021/06/Chocolate-Milkshake-5.jpg",
      description:
        "Nothing beats a creamy, thick chocolate shake alongside a nice burger and fries.Our chocolate milkshake is an exceptional treat, topped with a nice dollop of whipped cream.",
      recipe: "https://youtu.be/v-dBAqmGK7E",
    },
    {
      name: "Fruit Beer",
      image:
        "https://marketresearch.biz/wp-content/uploads/2018/09/fruit-beer-market.jpeg",
      description:
        "The world of fruit beer is more exciting than ever, offering many unique, fun, and refreshing brews to discover. It’s a category that stretches far beyond lemonade-beer shandies to include a great variety of fruits in nearly every style of beer.",
      recipe: "https://youtu.be/_RCKw4Mdvhk",
    },
    {
      name: "Raita",
      image:
        "https://sandhyahariharan.co.uk/wp-content/uploads/2020/06/Boondi-Raita-1-of-4-500x500.jpg",
      description:
        "Raita is a side dish from Indian Cuisine, made with yogurt, spices, herbs, vegetables and sometimes even with fruits. It is an integral part of Indian meal menus",
      recipe: "https://youtu.be/TIu4I-YqG5o",
    },
  ],
  rainy: [
    {
      name: "Pakore",
      image:
        "https://images.news18.com/ibnlive/uploads/2015/07/shutterstock_194798531-1.jpg?impolicy=website&width=0&height=0",
      description:
        "Mouth-watering pakoras and a cup of tea is a mind-boggling combination, during the monsoon. You can alter your choice by choosing from wide range of onion pakora, potato pakora, cauliflower pakora and paneer pakora.",
      recipe: "https://youtu.be/vJ-x-2Klnhg",
    },
    {
      name: "Samosa",
      image:
        "https://images.news18.com/ibnlive/uploads/2015/07/shutterstock_260410355.jpg?impolicy=website&width=0&height=0",
      description:
        "You can choose from Pasta samosa, chilly-paneer samosa, nutria- samosa, keema samosa and numerous other varities. So, without giving a second thought just drool on them.",
      recipe: "https://youtu.be/EKPAfUCn_Jo",
    },
    {
      name: "Masala Chai",
      image:
        "https://images.news18.com/ibnlive/uploads/2015/07/shutterstock_284446268-1.jpg?impolicy=website&width=0&height=0",
      description:
        "A cup of strong, masala chai consisting of ginger and green cardamom will be a complete stress-buster.",
      recipe: "https://youtu.be/WMAkl6_MrMM",
    },
    {
      name: "Kachori",
      image:
        "https://images.news18.com/ibnlive/uploads/2015/07/shutterstock_189907967.jpg?impolicy=website&width=0&height=0",
      description:
        "Imagine the taste of lip-smacking kachoris, served with spicy potato curry, what else do you need, while raining?",
      recipe: "https://youtu.be/7Mz63qPbiZw",
    },
    {
      name: "Jalebi",
      image:
        "https://images.news18.com/ibnlive/uploads/2015/07/shutterstock_259664951-1.jpg?impolicy=website&width=0&height=0",
      description:
        "Garma-garam thin jalebis tastes mouth-watering and delicious in monsoon. Circular shaped jalebis dipped in sugary syrup with the combination of samosas are just near to perfect.",
      recipe: "https://youtu.be/0w471_wMPP0",
    },
    {
      name: "Bhutta",
      image:
        "https://images.news18.com/ibnlive/uploads/2015/07/shutterstock_199170482.jpg?impolicy=website&width=0&height=0",
      description:
        "Item for spicy food lovers. It tastes even better with your friends and loved ones, sitting in open, enjoying stove-hot bhutta. Just make sure that your bhutta is garnished properly with loads of lemon juice and spice",
      recipe: "https://youtu.be/616ty9V6g-0",
    },
  ],
};

module.exports = {
  foodItems,
};
