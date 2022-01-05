const regionalFoods = {
  north: [
    {
      name: "Chole Bhature",
      image:
        "https://i.ndtvimg.com/i/2015-01/625-chole-bhature_625x350_41420696838.jpg",
      description:
        "Mouth-watering meal straight from the Punjabi kitchen - garma garam bhature with chickpeas cooked in assorted spices. What's better than that?",
    },
    {
      name: "Aloo Samosa",
      image: "https://i.ndtvimg.com/i/2015-06/samosa_625x350_51435293637.jpg",
      description:
        "The perfect companion for your evening tea. A fried snack stuffed with a mixture of potato and peas.",
    },
    {
      name: "Rogan Josh",
      image:
        "https://i.ndtvimg.com/i/2015-05/roganjosh_625x350_81430903997.jpg",
      description:
        "Originated in Kashmir, we bring you the signature dish of the valley. This one is an all-time favourite among meat lovers.",
    },
    {
      name: "Stuffed Bati",
      image:
        "https://c.ndtvimg.com/2019-01/mp9n6kn8_bati_625x300_03_January_19.jpg",
      description:
        "This Rajasthani bread snack is cooked in ghee and served with chutney and dal. Bati is usually stuffed with paneer and spices.",
    },
    {
      name: "Butter Chicken",
      image:
        "https://i.ndtvimg.com/i/2015-05/butter-chicken_625x350_81430902427.jpg",
      description:
        "A perfect dinner party recipe, this North-Indian style chicken recipe is made throughout the country with equal zest. Enjoy this creamy chicken dish, marinated overnight and then cooked to perfection.",
    },
  ],
  south: [
    {
      name: "Masala Dosa",
      image:
        "https://i.ndtvimg.com/i/2017-08/masala-dosa_620x330_81504097859.jpg",
      description:
        "The king of all dosas! Listed as one of the world's most delicious foods, a masala dosa never fails to impress!",
    },
    {
      name: "Idli",
      image:
        "https://cdn.tasteatlas.com//Images/Dishes/7389072b562f4dd6b08608b2692b4728.jpg?w=905&h=510",
      description:
        "Idli is a traditional, savory Indian cake that is a popular breakfast item in numerous South Indian households, although it can be found throughout the country. It is made with a batter consisting of fermented lentils and rice, which is then steamed.",
    },
    {
      name: "Upma",
      image:
        "https://cdn.tasteatlas.com//Images/Dishes/e4e3193046014f5bb9d0b6dc838d0f53.jpg?w=905&h=510",
      description:
        "Upma is a wholesome Indian dish made with dry semolina or rice flour, cooked into a thick porridge. Traditionally served hot for breakfast, its taste is slightly bland, so various nuts, beans, and spices such as turmeric and chiles are commonly added to the dish in order to improve its flavors.",
    },
    {
      name: "Pongal",
      image:
        "https://cdn.tasteatlas.com//images/dishes/a4a2780dee054e0c9e764bb7ad1ae820.jpg?w=905&h=510",
      description:
        "Pongal is a sweet rice dish that is usually eaten during special or ceremonial occasions. It is usually cooked in a clay pot over an open fire. Milk and water are boiled first, and according to Tamil beliefs, if the liquid spills over the pot it will bring good luck and prosperity to the family.",
    },
    {
      name: "Pulihora",
      image:
        "https://cdn.tasteatlas.com//images/dishes/2a00c116070d46a7a8dd91c0e086f76d.jpg?w=905&h=510",
      description:
        "Pulihora is a popular south Indian dish that can easily be found in the states of Andhra Pradesh, Tamil Nadu, and Karnataka. It consists of rice, turmeric, tamarind, curry leaves, coriander, ginger, and green chiles.",
    },
  ],
  east: [
    {
      name: "Rasmalai",
      image: "https://i.ndtvimg.com/i/2018-04/rasmalai_650x400_51523430877.jpg",
      description:
        "An all time favorite east- Indian dessert, rasmalai is a delectable Bengali dessert, freshly made with chhena balls and soaked in malai. A must try at home during the festive season.",
    },
    {
      name: "Rasgulla",
      image:
        "https://i.ndtvimg.com/i/2017-10/rasgulla-recipe_620x330_51508133855.jpg",
      description:
        "The Indian sweet popular all across the world, Rasgulla or Roshogulla is a proud traditional Bengali dessert that can make all of us go weak in knees. Spongy and syrupy, rasgullas are paneer and chhena-based sweets that are prepared and served on various festivals and special occasions.",
    },
    {
      name: "Dalma",
      image:
        "https://i.ndtvimg.com/i/2015-12/dalma-625_625x350_61451289446.jpg",
      description:
        "A traditional recipe from Odisha, dalma is nutritionally rich with split chickpeas cooked along with raw papaya, potatoes and a host of masalas. It is a quick and easy recipe that you can serve in lunch along with cooked rice or roti.",
    },
    {
      name: "Momos",
      image: "https://i.ndtvimg.com/i/2017-12/momos_620x330_71513321032.jpg",
      description:
        "Scrumptious dumplings stuffed with a variety of ground meat or vegetables, momos are a delight from Sikkim that are immensely loved by kids and adults alike.",
    },
    {
      name: "Litti Chokha",
      image:
        "https://c.ndtvimg.com/r1m5m4n8_litti-chokha_625x300_24_August_18.jpg",
      description:
        "A special delicacy from Bihar, litti chokha is the perfect dish to represent the earthiness of the state. Litti is a ball of dough stuffed with sattu and roasted until cooked, dipped in desi ghee, while chokha is a blend of baingan (eggplant), tomatoes, potatoes and a myriad of spices.",
    },
  ],
  west: [
    {
      name: "Vada Pav",
      image: "https://i.ndtvimg.com/i/2017-10/vada-pav_650x400_71507208817.jpg",
      description:
        " The fact that most people agree that vada pav isn't just another snack but a way of life in Mumbai, clearly put it on a higher pedestal. A soft and spongy pav, stuffed with a golden-fried spicy vada, laced in a mouth-watering tamarind and coriander chutneys with a sprinkle of masala ",
    },
    {
      name: "Methi ka Thepla",
      image:
        "https://i.ndtvimg.com/i/2017-09/methi-ka-thepla_620x330_71506063533.jpg",
      description:
        "Delicious Indian flatbread from the state of Gujarat, methi ka thepla is made from fresh fenugreek (methi) leaves, flour, ginger, garlic, green chillies, coriander powder, sugar and salt, together kneaded along with yogurt.",
    },
    {
      name: "Dhokla",
      image:
        "http://punampaul.com/wp-content/uploads/2018/05/gujarati-dhokla-recipe.jpg",
      description:
        "Undoubtedly the king of Gujarati snacks, dhokla is one snack that has gone beyond borders and made its place on the global palate. Besan, chillies, turmeric, lemon and curd made into a batter and baked to a spongy delight.",
    },
    {
      name: "Laal Maas",
      image:
        "https://c.ndtvimg.com/2019-05/rlhqae2g_lal-maas_625x300_03_May_19.jpg",
      description:
        "It is a fiery red meat dish that is traditionally prepared with a special variety of Rajasthani red chillies known as mathania.",
    },
    {
      name: "Zunka Bhakri",
      image:
        "https://www.whiskaffair.com/wp-content/uploads/2020/03/Zunka-2-3.jpg",
      description:
        "A traditional Maharashtrian dish, zunka bhakri is a sabzi made with chickpea or besan flour. It is a flavourful mix of red chilli, curry leaves and turmeric, stirred in water together and cooked with mustard seeds, cumin seeds, ginger, garlic and chillies.",
    },
  ],
};

module.exports = {
  regionalFoods,
};
