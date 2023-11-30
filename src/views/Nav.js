import { ActivityIndicator, View, TextInput, TouchableOpacity, Pressable } from "react-native";
import { useState, useEffect } from "react";
import { styles } from "../styles/styles";
import { FontAwesomeIcon } from '@fortawesome/react-native-fontawesome';
import { faMagnifyingGlass, faLeftLong, faRightLong } from '@fortawesome/free-solid-svg-icons';
import api from "../config/api";
import ThemeButton from "../components/ThemeButton";
import Card from "../components/Card";
import Text from "../components/Text";

export default function Nav(props) {
    const [search, setSeatch] = useState(localStorage.getItem('search') ? String(localStorage.getItem('search')) : "")
    const [cards, setCards] = useState([])
    const [loading, setLoading] = useState(true)
    const [page, setPage] = useState(0)
    var height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
    var limit = parseInt((height - 200) / 75) - 1;

    const fetchData = async () => {
        try {
            setLoading(true);
            const response = await api.get("apartment", {
                params: {
                    offset: 1 + (limit * page),
                    limit: limit < 1 ? 1 : limit,
                },
            });
            setCards(response.data);
            setLoading(false);
        } catch (error) {
            console.error("Erro ao buscar dados da API:", error);
        }
    };

    useEffect(() => {
        const fetchAndSetData = async () => {
            await fetchData();
        };
    
        fetchAndSetData();
    }, [page]);

    async function previousPage() {
        if(page > 0) {
            await setPage(page - 1);
        }
    }

    async function nextPage() {
        if(cards.length > 0) {
            await setPage(page + 1);
        }
    }

    return (<>
        <ThemeButton />
        <View style={{ display: "flex", flexDirection: "row", gap: "5px", paddingInline: "50px" }}>
            <TextInput
                value={search}
                onChangeText={(value) => { setSeatch(value); localStorage.setItem('search', value); }}
                style={{ ...styles.textInput, marginTop: "10%" }} />
            <TouchableOpacity style={{ display: "flex", justifyContent: "flex-end" }}>
                <FontAwesomeIcon icon={faMagnifyingGlass} style={styles.icon} size={20} />
            </TouchableOpacity>
        </View>
        <View style={{ padding: "50px", gap: "15px" }}>
            {loading ? (
                <ActivityIndicator size="large" />
            ) : (
                cards.map((card, index) => (
                    <Card
                        key={index}
                        condominio={card.local.condominium}
                        bloco={card.local.block}
                        num={card.number}
                        owner={card.owner}
                        residents={card.residents ? card.residents.map(resident => resident.name) : null}
                    />
                ))
            )}
        </View>
        <View style={{display: "flex", flexDirection: "row", justifyContent: "space-around", width: "100%"}}>
            <TouchableOpacity onPress={() => previousPage()}>
                <FontAwesomeIcon icon={faLeftLong} style={styles.icon} size={25} />
            </TouchableOpacity>
            <Text>Pagina {page + 1}</Text>
            <TouchableOpacity onPress={() => nextPage()}>
                <FontAwesomeIcon icon={faRightLong} style={styles.icon} size={25} />
            </TouchableOpacity>
        </View>
    </>);
}