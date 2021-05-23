typedef struct soe_data_packet {
	uint8_t _has_zflag_crc;
	uint8_t zflag;
	long long _data_sz;
	char *data;
	uint16_t seq_num;
	uint16_t crc;
} soe_data_t;

const char *SOE_RUDP_VERSION = "CGAPI_527";

soe_data_t *
parse_data_packet_multi(const uint8_t* buf, size_t len)
{
	soe_data_packet *data = malloc(sizeof(soe_data_packet));
	if(data == NULL) return NULL;

	data->_has_zflag_crc = 0;

	data->_data_sz = len - 4;

	data->data = malloc(data->_data_sz);
	if(data->data == NULL) {
		free(data);
		return NULL;
	}

	int i;
	for(i = 0; i < data->_data_sz; i++)
		data->data[i] = buf[i];

	memcpy(data->data, buf + 4, data->_data_sz);

	data->zflag = 0;

	data->seq_num = n16(buf, 2);

	return data;
}
